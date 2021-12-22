
drop table if exists oauth_client_details;
create table oauth_client_details (
  client_id character varying(255) PRIMARY KEY,
  resource_ids character varying(255),
  client_secret character varying(255),
  scope character varying(255),
  authorized_grant_types character varying(255),
  web_server_redirect_uri character varying(255),
  authorities character varying(255),
  access_token_validity INTEGER,
  refresh_token_validity INTEGER,
  additional_information character varying(4096),
  autoapprove character varying(255)
);

drop table if exists oauth_client_token;
create table oauth_client_token (
  token_id character varying(255),
  token bytea,
  authentication_id character varying(255) PRIMARY KEY,
  user_name character varying(255),
  client_id character varying(255)
);

drop table if exists oauth_access_token;
create table oauth_access_token (
  token_id character varying(255),
  token bytea,
  authentication_id character varying(255) PRIMARY KEY,
  user_name character varying(255),
  client_id character varying(255),
  authentication bytea,
  refresh_token character varying(255)
);

drop table if exists oauth_refresh_token;
create table oauth_refresh_token (
  token_id character varying(255),
  token bytea,
  authentication bytea
);

drop table if exists oauth_code;
create table oauth_code (
  code character varying(255)
);

drop table if exists oauth_approvals;
create table oauth_approvals (
    userId character varying(255),
    clientId character varying(255),
    scope character varying(255),
    status character varying(10),
    lastModifiedAt timestamp without time zone,
    expiresAt timestamp without time zone
);

drop table if exists ClientDetails;
create table ClientDetails (
  appId character varying(255) PRIMARY KEY,
  resourceIds character varying(255),
  appSecret character varying(255),
  scope character varying(255),
  grantTypes character varying(255),
  redirectUrl character varying(255),
  authorities character varying(255),
  access_token_validity INTEGER,
  refresh_token_validity INTEGER,
  additionalInformation character varying(4096),
  autoApproveScopes character varying(255)
);
 DROP TABLE if exists  users;
CREATE TABLE users
(
  username character varying(55) NOT NULL,
  password character varying(100),
  role character varying(100),
  email character varying(100),
  name character varying(100),
  id integer NOT NULL,
  CONSTRAINT users_pkey PRIMARY KEY (username)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE users
  OWNER TO postgres;



drop table if exists employees;
create table employees(
    id_employee INT PRIMARY KEY ,
    first_name character varying(55),
    last_name character varying(55),
    age INT
);

insert into users (id,username, password, role) values (1,'admin', 'admin', 'ROLE_ADMIN');
insert into employees (id_employee,first_name, last_name, age) values (1,'John', 'Doe', 25);