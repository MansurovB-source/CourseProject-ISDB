--------------------------------------------------------------------------------------------------------------
-- increment client number delivery_place
CREATE OR REPLACE FUNCTION inc_DelClientNum() RETURNS TRIGGER AS
$$
BEGIN
    UPDATE delivery_places
    SET client_num = client_num + 1
    WHERE delivery_places.id_delivery_place = NEW.id_delivery_place;
    RETURN NEW;
END;
$$ LANGUAGE plpgsql;



CREATE TRIGGER inc_DelClientNum
    AFTER INSERT
    ON clients
    FOR EACH ROW
EXECUTE PROCEDURE inc_DelClientNum();
--------------------------------------------------------------------------------------------------------------

--------------------------------------------------------------------------------------------------------------
-- decrement client number delivery_place
CREATE OR REPLACE FUNCTION dec_DelClientNum() RETURNS TRIGGER AS
$$
BEGIN
    UPDATE delivery_places
    SET client_num = client_num - 1
    WHERE delivery_places.id_delivery_place = OLD.id_delivery_place;
    RETURN NEW;
END;
$$ LANGUAGE plpgsql;



CREATE TRIGGER dec_DelClientNum
    AFTER DELETE
    ON clients
    FOR EACH ROW
EXECUTE PROCEDURE dec_DelClientNum();
--------------------------------------------------------------------------------------------------------------


--------------------------------------------------------------------------------------------------------------
-- increment worker number
CREATE OR REPLACE FUNCTION inc_WorkerNum() RETURNS TRIGGER AS
$$
BEGIN
    UPDATE factories
    SET worker_num = worker_num + 1
    WHERE factories.id_factory = NEW.id_factory;
    RETURN NEW;
END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER int_WorkerNum
    AFTER INSERT
    ON providers
    FOR EACH ROW
EXECUTE PROCEDURE inc_WorkerNum();
--------------------------------------------------------------------------------------------------------------


--------------------------------------------------------------------------------------------------------------
-- decrement worker number
CREATE OR REPLACE FUNCTION dec_WorkerNum() RETURNS TRIGGER AS
$$
BEGIN
    UPDATE factories
    SET worker_num = worker_num - 1
    WHERE factories.id_factory = OLD.id_factory;
    RETURN NEW;
END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER int_WorkerNum
    AFTER DELETE
    ON providers
    FOR EACH ROW
EXECUTE PROCEDURE dec_WorkerNum();
--------------------------------------------------------------------------------------------------------------


--------------------------------------------------------------------------------------------------------------
-- schedule
CREATE OR REPLACE FUNCTION make_Schedule() RETURNS TRIGGER AS
$$
DECLARE
    del_place integer;
    provider  integer;
    weight    integer;

BEGIN
    SELECT id_delivery_place FROM clients WHERE clients.id_client = NEW.from INTO del_place;
    SELECT id_provider FROM providers WHERE providers.id_delivery_place = del_place INTO provider;
    SELECT storages.sausages_weight
    FROM storages
    WHERE (storages.id_sausage = NEW.id_sausage AND
           storages.id_factory = (SELECT providers.id_factory FROM providers WHERE (providers.id_provider = provider)))
    INTO weight;
    IF (weight > NEW.sausages_weight) THEN
        UPDATE storages
        SET sausages_weight = sausages_weight - NEW.sausages_weight
        WHERE (storages.id_sausage = NEW.id_sausage AND
               storages.id_factory =
               (SELECT providers.id_factory FROM providers WHERE (providers.id_provider = provider)));
        IF EXISTS(SELECT *
                  FROM order_shedule
                  WHERE order_shedule.id_provider = provider
                    AND order_shedule.id_sausage =
                        NEW.id_sausage
                    AND order_shedule.del_time =
                        (current_date + 1)) THEN
            UPDATE order_shedule
            SET sausages_weight = sausages_weight + NEW.sausages_weight
            WHERE order_shedule.id_provider = provider
              AND order_shedule.id_sausage =
                  NEW.id_sausage
              AND order_shedule.del_time =
                  current_date + 1;
        ELSE
            INSERT INTO order_shedule(id_provider, id_sausage, sausages_weight, del_time)
            VALUES (provider, NEW.id_sausage, NEW.sausages_weight, current_date + 1);
        END IF;
    ELSE
        RAISE EXCEPTION 'We do not have this product in our storage, it would be soon';
    END IF;
    NEW.from = provider;
    NEW.ord_time = localtimestamp;
    RETURN NEW;
END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER make_Schedule
    BEFORE INSERT
    ON orders
    FOR EACH ROW
EXECUTE PROCEDURE make_Schedule();
--------------------------------------------------------------------------------------------------------------


--------------------------------------------------------------------------------------------------------------
CREATE OR REPLACE FUNCTION payment() RETURNS TRIGGER AS
$$
DECLARE
    client_payment   integer;
    price            integer;
    factory          integer;
    provider_payment integer;
BEGIN
    SELECT id_client_payment
    FROM client_payments
    WHERE (id_client = NEW.from AND id_provider = NEW.to AND dept_date = current_date AND
           paying = FALSE)
    INTO client_payment;
    SELECT price FROM sausages WHERE (sausages.id_sausage = New.id_sausage) INTO price;
    IF client_payment THEN
        UPDATE client_payments
        SET sum = sum + price * NEW.sausages_weight
        WHERE id_client_payment = client_payment;
    ELSE
        INSERT INTO client_payments(id_client, id_provider, sum, dept_date, paying, payment_date)
        VALUES (NEW.from, NEW.to, price * NEW.sausages_weight, current_date, FALSE, NULL);
    END IF;

    SELECT id_factory FROM providers WHERE providers.id_provider = NEW.to INTO factory;
    SELECT id_provider_payment
    FROM providers_payments
    WHERE (id_provider = NEW.to AND id_factory = factory AND dept_date = current_date AND paying = FALSE)
    INTO provider_payment;
    IF provider_payment THEN
        UPDATE providers_payments
        SET sum = sum + price * NEW.sausages_weight
        WHERE id_provider_payment = provider_payment;
    ELSE
        INSERT INTO providers_payments(id_provider, id_factory, sum, dept_date, paying, payment_date)
        VALUES (NEW.to, factory, price * NEW.sausages_weight, current_date, FALSE, NULL);
    END IF;
END
$$ LANGUAGE plpgsql;

CREATE TRIGGER pay
    AFTER INSERT
    ON orders
    FOR EACH ROW
EXECUTE PROCEDURE payment();
--------------------------------------------------------------------------------------------------------------

---------------------------------------------------------------------------------------------------------------
CREATE OR REPLACE FUNCTION client_pay() RETURNS TRIGGER AS
$$
DECLARE
    del_place integer;
    provider  integer;
BEGIN
    SELECT id_delivery_place FROM clients WHERE clients.id_client = NEW.id_client INTO del_place;
    SELECT id_provider FROM providers WHERE providers.id_delivery_place = del_place INTO provider;
    NEW.id_provider = provider;
    NEW.paying = TRUE;
    NEW.payment_date = current_date;
    RETURN NEW;
END
$$ LANGUAGE plpgsql;

CREATE TRIGGER client_pay
    BEFORE UPDATE
    ON client_payments
    FOR EACH ROW
EXECUTE PROCEDURE client_pay();
---------------------------------------------------------------------------------------------------------------


---------------------------------------------------------------------------------------------------------------
CREATE OR REPLACE FUNCTION provider_pay() RETURNS TRIGGER AS
$$
DECLARE
    factory integer;
BEGIN
    SELECT id_factory FROM providers WHERE providers.id_provider = NEW.id_provider INTO factory;
    NEW.id_factory = factory;
    NEW.paying = TRUE;
    NEW.payment_date = current_date;
END
$$ language plpgsql;

CREATE TRIGGER client_pay
    BEFORE UPDATE
    ON providers_payments
    FOR EACH ROW
EXECUTE PROCEDURE provider_pay();
---------------------------------------------------------------------------------------------------------------


--------------------------------------------------------------------------------------------------------------
-- client returning to provider
CREATE OR REPLACE FUNCTION return_client() RETURNS TRIGGER AS
$$
DECLARE
    del_place integer;
    provider  integer;
BEGIN
    SELECT id_delivery_place FROM clients WHERE clients.id_client = NEW.from INTO del_place;
    SELECT id_provider FROM providers WHERE providers.id_delivery_place = del_place INTO provider;
    NEW.to = provider;
    NEW.ret_time = localtime;
    RETURN NEW;
END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER return_client
    BEFORE INSERT
    ON return_client
    FOR EACH ROW
EXECUTE PROCEDURE return_client();
--------------------------------------------------------------------------------------------------------------


--------------------------------------------------------------------------------------------------------------
-- provider returning to factory
CREATE OR REPLACE FUNCTION return_provider() RETURNS TRIGGER AS
$$
DECLARE
BEGIN
    SELECT id_factory FROM providers WHERE providers.id_provider = NEW.from INTO NEW.to;
    NEW.ret_time = localtime;
    RETURN NEW;
END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER return_provider
    BEFORE INSERT
    ON return_provider
    FOR EACH ROW
EXECUTE PROCEDURE return_provider();
--------------------------------------------------------------------------------------------------------------