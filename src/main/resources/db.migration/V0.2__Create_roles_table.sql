CREATE TABLE public.tb_roles (
  role_id int8 NOT NULL,
  created_at timestamp(6) NULL,
  "name" varchar(255) NOT NULL,
  updated_at timestamp(6) NULL,
  CONSTRAINT tb_roles_name_check CHECK (((name)::text = ANY ((ARRAY['USER'::character varying, 'ADMIN'::character varying, 'DEV'::character varying])::text[]))),
  CONSTRAINT tb_roles_pkey PRIMARY KEY (role_id),
  CONSTRAINT uk8ghpmg7galg4xu5qu0feu2cmi UNIQUE (name)
);
