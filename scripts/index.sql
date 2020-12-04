CREATE INDEX storages_sausage_factory ON storages (id_factory, id_sausage);
CREATE INDEX order_schedule_provider_sausage_deliv_time ON order_schedule (id_provider, id_sausage, del_time);
CREATE INDEX return_client_from_to_sausage_rel_time ON return_client (_from, _to, id_sausage, ret_time);
CREATE INDEX return_provider_from_to_sausage_rel_time ON return_client (_from, _to, id_sausage, ret_time);