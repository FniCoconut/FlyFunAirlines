SELECT * FROM flyfunairlines.flight f WHERE f.F_SALIDA < date_add(sysdate(), interval 20 day) AND f.ID_FLY IN(SELECT o.VUELO FROM flyfunairlines.occupation o WHERE o.BOOKING_CODE='otmh82x8');
select * from flyfunairlines.air_connect where ID_CONNECT in (SELECT CONNECTION FROM flyfunairlines.flight f WHERE f.F_SALIDA < date_add(sysdate(), interval 10 day) AND f.ID_FLY IN(SELECT o.VUELO FROM flyfunairlines.occupation o WHERE o.BOOKING_CODE='abm657en'));
select * from flyfunairlines.airport where ID_AIRPORT in (select T_ORIGEN from flyfunairlines.air_connect where ID_CONNECT = 2) or ID_AIRPORT in (select T_DESTINO from flyfunairlines.air_connect where ID_CONNECT = 2);
select * from flyfunairlines.airport where ID_AIRPORT in (select T_ORIGEN from flyfunairlines.air_connect where ID_CONNECT = 2);
select p.*, o.ASIENTO from flyfunairlines.passenger p, flyfunairlines.occupation o where o.VUELO=4 and p.ID_PASSENGER in (select PASAJERO from flyfunairlines.occupation where BOOKING_CODE='otmh82x8' and PASAJERO=p.ID_PASSENGER and PASAJERO=o.PASAJERO);
select * from flyfunairlines.passenger p where p.CARGO=83;

SELECT * FROM flyfunairlines.booking where BK_CODE='abm657en';

select * from flyfunairlines.passenger where ID_PASSENGER in (select PASAJERO from flyfunairlines.occupation where BOOKING_CODE='otmh82x8' and ASIENTO is null);

select * from flyfunairlines.flight where F_SALIDA < date_add(sysdate(), interval 20 day) and ID_FLY in(select VUELO from flyfunairlines.occupation where BOOKING_CODE='otmh82x8') order by F_SALIDA desc;

select p.*, o.ASIENTO from flyfunairlines.passenger p, flyfunairlines.occupation o where o.VUELO=1 and p.ID_PASSENGER in (select PASAJERO from flyfunairlines.occupation where BOOKING_CODE='otmh82x8' and PASAJERO=p.ID_PASSENGER and PASAJERO=o.PASAJERO);

select * from flyfunairlines.occupation o where PASAJERO in (select ID_PASSENGER from flyfunairlines.passenger where ID_PASSENGER = o.PASAJERO) and o.BOOKING_CODE='otmh82x8' and o.VUELO=1;

select * from service where ID_SERVICE in (select SERVICIO from occupation_service where OCUPACION in (select ID_OCP from occupation where PASAJERO=83 and VUELO=4));