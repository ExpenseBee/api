CREATE TABLE public.tb_user (
  user_id uuid NOT NULL,
  email varchar(255) NULL,
  "password" varchar(255) NULL,
  first_name varchar(255) NULL,
  last_name varchar(255) NULL,
  created_at timestamp(6) NULL,
  updated_at timestamp(6) NULL,
  account_expired_at timestamp(6) NULL,
  credentials_expired_at timestamp(6) NULL,
  credential_non_expired bool NOT NULL DEFAULT TRUE,
  "enabled" bool NOT NULL DEFAULT TRUE,
  account_non_locked bool NULL DEFAULT TRUE,
  CONSTRAINT tb_user_pkey PRIMARY KEY (user_id)
);
