ALTER TABLE public.tb_user
  ALTER COLUMN account_non_locked SET DEFAULT TRUE,
  ALTER COLUMN credential_non_expired SET DEFAULT TRUE,
  ALTER COLUMN enabled SET DEFAULT TRUE;
