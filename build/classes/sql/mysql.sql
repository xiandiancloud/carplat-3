drop database if exists eduplat;
create database eduplat character set utf8;
use eduplat;

/*==============================================================*/
/* Table: t_car    car表                                        */
/*==============================================================*/
drop table if exists t_car;
create table t_car
(
   id              int not null AUTO_INCREMENT,
   code             int(20) not null,
   card            char(100) not null,   
   primary key (id)   
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;



drop view if exists carview;
create view carview
    as
    select * from t_name
    union all
    select * from t_temp;

drop view if exists carinoutview;
create view carinoutview as select
		t_log.id as id,
		t_log.card as card,
		t_log.indate as indate,
		t_log.status as status,
		t_log.pos as pos,
		carview.id as code,
		carview.carposition as carposition,
		carview.caruser as caruser,
		carview.tel as tel,	
		carview.indate as alldate
		from t_log left join carview on (t_log.card=carview.card);
