select b.ID_BK, b.BK_CODE, b.IDA, b.VUELTA , o.ID_OCP, o.ASIENTO , p.NOMBRE, p.APELLIDOS, p.NIF, p. TIPO, f.ID_FLY, f.N_VUELO, f.F_SALIDA 
from flyfunairlines.booking b join flyfunairlines.occupation o join flyfunairlines.passenger p join flyfunairlines.flight f join flyfunairlines.air_connect ac, flyfunairlines.airport ap
where b.CLIENTE=23 and o.PASAJERO = p.ID_PASSENGER and o.VUELO= f.ID_FLY and f.CONNECTION=ac.ID_CONNECT and  ap.ID_AIRPORT