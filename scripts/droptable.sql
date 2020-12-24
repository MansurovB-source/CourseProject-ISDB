DO $$ DECLARE
    r RECORD;
BEGIN
    FOR r IN (SELECT tablename FROM pg_tables WHERE schemaname = current_schema()) LOOP
        EXECUTE 'DROP TABLE IF EXISTS ' || quote_ident(r.tablename) || ' CASCADE';
    END LOOP;
END $$;

DROP FUNCTION IF EXISTS get_Workers(id_factory integer) CASCADE;
DROP FUNCTION IF EXISTS get_clients_for_factory(id_factory integer) CASCADE;
DROP FUNCTION IF EXISTS get_clients_for_provider(id_provider integer) CASCADE;

DROP FUNCTION IF EXISTS inc_DelClientNum() CASCADE;
DROP FUNCTION IF EXISTS dec_DelClientNum() CASCADE;
DROP FUNCTION IF EXISTS inc_WorkerNum() CASCADE;
DROP FUNCTION IF EXISTS dec_WorkerNum() CASCADE;
DROP FUNCTION IF EXISTS make_Schedule() CASCADE;
DROP FUNCTION IF EXISTS payment() CASCADE;
DROP FUNCTION IF EXISTS client_pay() CASCADE;
DROP FUNCTION IF EXISTS provider_pay() CASCADE;
DROP FUNCTION IF EXISTS return_client() CASCADE;
DROP FUNCTION IF EXISTS return_provider() CASCADE;

DROP INDEX IF EXISTS storages_sausage_factory;
DROP INDEX IF EXISTS order_schedule_provider_sausage_deliv_time;
DROP INDEX IF EXISTS return_client_from_to_sausage_rel_time;
DROP INDEX IF EXISTS return_provider_from_to_sausage_rel_time;