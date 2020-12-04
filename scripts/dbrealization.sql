CREATE TABLE humans
(
    id_human   serial,
    name       varchar(32) NOT NULL,
    surname    varchar(32) NOT NULL,
    birth_date date        NOT NULL CHECK (birth_date < now()),
    contacts   varchar(32) NOT NULL,
    address    text        NOT NULL,
    PRIMARY KEY (id_human)
);

CREATE TABLE sausages
(
    id_sausage serial,
    name       text          NOT NULL,
    price      decimal(7, 2) NOT NULL CHECK (price > 0),
    PRIMARY KEY (id_sausage)
);

CREATE TABLE locations
(
    id_location serial,
    country     text NOT NULL,
    city        text NOT NULL,
    PRIMARY KEY (id_location)
);

CREATE TABLE delivery_places
(
    id_delivery_place serial,
    id_location       integer NOT NULL,
    address           text    NOT NULL,
    client_num        integer,
    PRIMARY KEY (id_delivery_place),
    FOREIGN KEY (id_location) REFERENCES locations (id_location)
        ON UPDATE CASCADE
        ON DELETE CASCADE
);

CREATE TABLE factories
(
    id_factory  serial,
    name        text,
    id_location integer NOT NULL,
    worker_num  integer,
    PRIMARY KEY (id_factory),
    FOREIGN KEY (id_location) REFERENCES locations (id_location)
);

CREATE TABLE animals
(
    id_animal   serial,
    name        text    NOT NULL,
    id_location integer NOT NULL,
    PRIMARY KEY (id_animal),
    FOREIGN KEY (id_location) REFERENCES locations (id_location)
);

CREATE TABLE cars
(
    id_car     serial,
    capacity   integer NOT NULL CHECK (capacity > 0),
    id_factory integer NOT NULL,
    PRIMARY KEY (id_car),
    FOREIGN KEY (id_factory) REFERENCES factories (id_factory)
        ON UPDATE CASCADE
        ON DELETE CASCADE
);

CREATE TABLE subscriptions
(
    id_subscription serial,
    name            text    NOT NULL,
    price           integer NOT NULL CHECK (price > 0),
    discount        integer NOT NULL CHECK (discount >= 0 AND discount < 100),
    PRIMARY KEY (id_subscription)
);

CREATE TABLE subs_sausages
(
    id_subscription integer NOT NULL,
    id_sausage      integer NOT NULL,
    PRIMARY KEY (id_subscription, id_sausage),
    FOREIGN KEY (id_subscription) REFERENCES subscriptions (id_subscription)
        ON UPDATE CASCADE
        ON DELETE CASCADE,
    FOREIGN KEY (id_sausage) REFERENCES sausages (id_sausage)
        ON UPDATE CASCADE
        ON DELETE CASCADE
);

CREATE TABLE factory_sausages
(
    id_factory integer NOT NULL,
    id_sausage integer NOT NULL,
    PRIMARY KEY (id_factory, id_sausage),
    FOREIGN KEY (id_factory) REFERENCES factories (id_factory)
        ON UPDATE CASCADE
        ON DELETE CASCADE,
    FOREIGN KEY (id_sausage) REFERENCES sausages (id_sausage)
        ON UPDATE CASCADE
        ON DELETE CASCADE
);

CREATE TABLE farms
(
    id_factory  integer NOT NULL,
    id_location integer NOT NULL,
    id_animal   integer NOT NULL,
    PRIMARY KEY (id_factory, id_location, id_animal),
    FOREIGN KEY (id_factory) REFERENCES factories (id_factory)
        ON UPDATE CASCADE
        ON DELETE CASCADE,
    FOREIGN KEY (id_location) REFERENCES locations (id_location)
        ON UPDATE CASCADE
        ON DELETE CASCADE,
    FOREIGN KEY (id_animal) REFERENCES animals (id_animal)
        ON UPDATE CASCADE
        ON DELETE CASCADE
);

CREATE TABLE storages
(
    id_storage      serial,
    id_factory      integer NOT NULL,
    id_sausage      integer NOT NULL,
    sausages_weight float   NOT NULL CHECK (sausages_weight > 0),
    FOREIGN KEY (id_factory) REFERENCES factories (id_factory)
        ON UPDATE CASCADE
        ON DELETE CASCADE,
    FOREIGN KEY (id_sausage) REFERENCES sausages (id_sausage)
);

CREATE TABLE providers
(
    id_provider       serial,
    id_human          integer       NOT NULL,
    salary            decimal(8, 2) NOT NULL CHECK (salary > 0),
    id_factory        integer       NOT NULL,
    id_delivery_place integer,
    PRIMARY KEY (id_provider),
    FOREIGN KEY (id_human) REFERENCES humans (id_human)
        ON UPDATE CASCADE
        ON DELETE CASCADE,
    FOREIGN KEY (id_factory) REFERENCES factories (id_factory)
        ON UPDATE CASCADE
        ON DELETE CASCADE,
    FOREIGN KEY (id_delivery_place) REFERENCES delivery_places (id_delivery_place)
        ON UPDATE CASCADE
        ON DELETE SET NULL
);

CREATE TABLE clients
(
    id_client         serial,
    id_human          integer NOT NULL,
    id_delivery_place integer,
    id_subscription   integer,
    PRIMARY KEY (id_client),
    FOREIGN KEY (id_human) REFERENCES humans (id_human)
        ON UPDATE CASCADE
        ON DELETE CASCADE,
    FOREIGN KEY (id_delivery_place) REFERENCES delivery_places (id_delivery_place)
        ON UPDATE CASCADE
        ON DELETE SET NULL,
    FOREIGN KEY (id_subscription) REFERENCES subscriptions (id_subscription)
        ON UPDATE CASCADE
        ON DELETE SET NULL
);

CREATE TABLE orders
(
    id_order        serial,
    _from           integer   NOT NULL,
    _to             integer   NOT NULL,
    id_sausage      integer   NOT NULL,
    sausages_weight float     NOT NULL CHECK (sausages_weight > 0),
    ord_time        timestamp NOT NULL,
    special         boolean   NOT NULL,
    PRIMARY KEY (id_order),
    FOREIGN KEY (_from) REFERENCES clients (id_client)
        ON UPDATE CASCADE
        ON DELETE CASCADE,
    FOREIGN KEY (_to) REFERENCES providers (id_provider)
        ON UPDATE CASCADE
        ON DELETE CASCADE,
    FOREIGN KEY (id_sausage) REFERENCES sausages (id_sausage)

);

CREATE TABLE order_schedule
(
    id_schedule     serial,
    id_provider     integer NOT NULL,
    id_sausage      integer NOT NULL,
    sausages_weight float   NOT NULL CHECK (sausages_weight > 0),
    del_time        date    NOT NULL,
    PRIMARY KEY (id_schedule),
    FOREIGN KEY (id_provider) REFERENCES providers (id_provider)
        ON UPDATE CASCADE
        ON DELETE CASCADE,
    FOREIGN KEY (id_sausage) REFERENCES sausages (id_sausage)
);

CREATE TABLE car_schedule
(
    id_schedule integer NOT NULL,
    id_car      integer NOT NULL,
    PRIMARY KEY (id_schedule, id_car)

);


CREATE TABLE clients_payments
(
    id_client_payment serial  NOT NULL,
    id_client         integer NOT NULL,
    id_provider       integer NOT NULL,
    sum               integer NOT NULL CHECK (sum > 0),
    dept_date         date    NOT NULL,
    paying            boolean NOT NULL,
    payment_date      date,
    PRIMARY KEY (id_client_payment),
    FOREIGN KEY (id_client) REFERENCES clients (id_client)
        ON UPDATE CASCADE
        ON DELETE CASCADE,
    FOREIGN KEY (id_provider) REFERENCES providers (id_provider)
        ON UPDATE CASCADE
        ON DELETE CASCADE
);

CREATE TABLE providers_payments
(
    id_provider_payment serial,
    id_provider         integer NOT NULL,
    id_factory          integer NOT NULL,
    sum                 integer NOT NULL CHECK (sum > 0),
    dept_date           date    NOT NULL,
    paying              boolean NOT NULL,
    payment_date        date,
    FOREIGN KEY (id_provider) REFERENCES providers (id_provider)
        ON UPDATE CASCADE
        ON DELETE CASCADE,
    FOREIGN KEY (id_factory) REFERENCES factories (id_factory)
        ON UPDATE CASCADE
        ON DELETE CASCADE
);

CREATE TABLE return_client
(
    id_return       serial,
    _from           integer NOT NULL,
    _to             integer,
    id_sausage      integer NOT NULL,
    sausages_weight float   NOT NULL CHECK (sausages_weight > 0),
    ret_time        time,
    PRIMARY KEY (id_return),
    FOREIGN KEY (_from) REFERENCES clients (id_client)
        ON UPDATE CASCADE
        ON DELETE CASCADE,
    FOREIGN KEY (_to) REFERENCES providers (id_provider)
        ON UPDATE CASCADE
        ON DELETE CASCADE,
    FOREIGN KEY (id_sausage) REFERENCES sausages (id_sausage)
);

CREATE TABLE return_provider
(
    id_ret_provider serial,
    _from           integer NOT NULL,
    _to             integer,
    id_sausage      integer NOT NULL,
    sausages_weight float   NOT NULL CHECK (sausages_weight > 0),
    ret_time        time,
    PRIMARY KEY (id_ret_provider),
    FOREIGN KEY (_from) REFERENCES providers (id_provider)
        ON UPDATE CASCADE
        ON DELETE CASCADE,
    FOREIGN KEY (_to) REFERENCES factories (id_factory)
        ON UPDATE CASCADE
        ON DELETE CASCADE,
    FOREIGN KEY (id_sausage) REFERENCES sausages (id_sausage)
);
