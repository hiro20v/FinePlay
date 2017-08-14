alter table COMPANY_NAMES drop constraint FKpohqee82rq2u73ck3yujbi463;
alter table ORGANIZATION_UNIT_NAMES drop constraint FKtgy1ak11cnjcd3x7t37pr7sem;
alter table ORGANIZATION_UNITS drop constraint FKc36lxm5lc90vm1kque8vr7cyd;
alter table ORGANIZATION_UNITS drop constraint FKaxl5lltie4tsoy0wug3btb5hl;
alter table ORGANIZATIONS drop constraint FKpl7c9r2u24rjr6pqmrcd9mxdq;
alter table USER_ROLES drop constraint FKk3qtke64s9k5pv5hoq6yyq7py;
alter table USERS drop constraint FKdeidj66mcx6k8vsoflveak3gj;
drop table if exists CHANGE_USERS cascade;
drop table if exists COMPANIES cascade;
drop table if exists COMPANY_NAMES cascade;
drop table if exists DATETIMES cascade;
drop table if exists INQUIRIES cascade;
drop table if exists ORGANIZATION_UNIT_NAMES cascade;
drop table if exists ORGANIZATION_UNITS cascade;
drop table if exists ORGANIZATIONS cascade;
drop table if exists REGIST_USERS cascade;
drop table if exists RESET_USERS cascade;
drop table if exists USER_ROLES cascade;
drop table if exists USERS cascade;
drop sequence if exists hibernate_sequence;
