CREATE TABLE public.user_roles (
  user_id uuid NOT NULL,
  role_id int8 NOT NULL,
  CONSTRAINT fkk2d4g0wq2983xkhjrucp8vg4b FOREIGN KEY (role_id) REFERENCES public.tb_roles(role_id),
  CONSTRAINT fklqb868dhpatxi3e1m1nu3ukr5 FOREIGN KEY (user_id) REFERENCES public.tb_user(user_id)
);
