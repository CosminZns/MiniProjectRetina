# MiniProjectRetina
NYPD Complaint data este un set de date public https://data.cityofnewyork.us/Public-Safety/NYPD-Complaint-Data-Historic/qgea-i56i al orasului NY pentru care Police Department a adunat date in timp cu privire la diverse evenimente ilegale: acte violenta, crime, consum de droguri, folosirea de arme, etc.
Dataset-ul este representat in forma CSV in care urmatoarele coloane (este posibil ca unele coloane sa se fi schimbat de-a lungul evolutiei structurii de date):
1	CMPLNT_NUM	Randomly generated persistent ID for each complaint 
2	CMPLNT_FR_DT	Exact date of occurrence for the reported event (or starting date of occurrence, if CMPLNT_TO_DT exists)
3	CMPLNT_FR_TM	Exact time of occurrence for the reported event (or starting time of occurrence, if CMPLNT_TO_TM exists)
4	CMPLNT_TO_DT	Ending date of occurrence for the reported event, if exact time of occurrence is unknown
5	CMPLNT_TO_TM	Ending time of occurrence for the reported event, if exact time of occurrence is unknown
6	RPT_DT	Date event was reported to police 
7	KY_CD	Three digit offense classification code
8	OFNS_DESC	Description of offense corresponding with key code
9	PD_CD	Three digit internal classification code (more granular than Key Code)
10	PD_DESC	Description of internal classification corresponding with PD code (more granular than Offense Description)
11	CRM_ATPT_CPTD_CD	Indicator of whether crime was successfully completed or attempted, but failed or was interrupted prematurely
12	LAW_CAT_CD	Level of offense: felony, misdemeanor, violation 
13	JURIS_DESC	Jurisdiction responsible for incident. Either internal, like Police, Transit, and Housing; or external, like Correction, Port Authority, etc.
14	BORO_NM	The name of the borough in which the incident occurred
15	ADDR_PCT_CD	The precinct in which the incident occurred
16	LOC_OF_OCCUR_DESC	Specific location of occurrence in or around the premises; inside, opposite of, front of, rear of
17	PREM_TYP_DESC	Specific description of premises; grocery store, residence, street, etc.
18	PARKS_NM	Name of NYC park, playground or greenspace of occurrence, if applicable (state parks are not included)
19	HADEVELOPT	Name of NYCHA housing development of occurrence, if applicable
20	X_COORD_CD	X-coordinate for New York State Plane Coordinate System, Long Island Zone, NAD 83, units feet (FIPS 3104)
21	Y_COORD_CD	Y-coordinate for New York State Plane Coordinate System, Long Island Zone, NAD 83, units feet (FIPS 3104)
22	Latitude	Latitude coordinate for Global Coordinate System, WGS 1984, decimal degrees (EPSG 4326) 
23	Longitude	Longitude coordinate for Global Coordinate System, WGS 1984, decimal degrees (EPSG 4326)


CERINTE/ OBIECTIVE
Realizarea unei aplicatie web care sa expuna urmatoarele endpoint-uri REST pe un sample de 1000 recorduri din setul de date disponibil:
GET endpoints:
•	dataset/stats/total – raspunde cu un JSON care contine numarul total de evenimente din dataset
•	dataset/stats/offenses – raspunde cu un JSON care contine numarul total de evenimente grupat dupa coloane KY_CD
DELETE endpoints:
•	/dataset/${id} – sterge din fisierul CSV evenimentul cu ID-ul specificat (fie ca request param, fie ca path variable) si raspunde cu true/false la succes.
POST endpoints:
•	/dataset – face post la un obiect de tipul JSON, care contine doar campurile CMPLNT_NUM, KY_CD si insereaza la sfarsitul fisierului o linie cu acele informatii.
Aplicatia web poate sa ruleze intr-un servlet sau server de aplicatii embedded sau local (.war).
