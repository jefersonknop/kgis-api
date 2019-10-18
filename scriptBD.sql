CREATE TABLE TIPO_EQUIPAMENTO(
ID SERIAL PRIMARY KEY,
DESCRICAO VARCHAR(255));

CREATE TABLE VEICULO(
ID SERIAL PRIMARY KEY,
INQUILINO_ID INTEGER,
TIPO VARCHAR(100),
PLACA VARCHAR(30),
NUMERO VARCHAR(50),
DESCRICAO VARCHAR(255),
SITUACAO VARCHAR(100),
INFORMACOES VARCHAR(500));


CREATE TABLE EQUIPAMENTO(
ID SERIAL PRIMARY KEY,
INQUILINO_ID INTEGER,
USUARIO_ID INTEGER,
VEICULO_ID INTEGER REFERENCES VEICULO,
TIPO VARCHAR(100),
NUMERO VARCHAR(50),
DESCRICAO VARCHAR(255),
SITUACAO VARCHAR(100),
INFORMACOES VARCHAR(500));

					
CREATE TABLE EVENTO(
ID SERIAL PRIMARY KEY,
INQUILINO_ID INTEGER,
EQUIPAMENTO_ID INTEGER REFERENCES EQUIPAMENTO,
TIPO VARCHAR(100),
DESCRICAO VARCHAR(255),
DATA_INICIO timestamptz,
DATA_FIM timestamptz,
SITUACAO VARCHAR(100),
INFORMACOES VARCHAR(500));			
					
					
CREATE EXTENSION postgis;	
					
					
		

					
					
	
					

		
CREATE TABLE registro (
id SERIAL PRIMARY KEY,
INQUILINO_ID INTEGER,
EVENTO_ID INTEGER REFERENCES EVENTO,
TIPO VARCHAR(100),
DESCRICAO VARCHAR(255),
DATAHORA timestamptz,
velocidade float,
altitude float,
temperatura float,
umidade float,
LONGITUDE FLOAT,
LATITUDE FLOAT,
SITUACAO VARCHAR(100),
INFORMACOES VARCHAR(500)
);
			
			
CREATE TABLE ponto_geo (
id SERIAL PRIMARY KEY,
INQUILINO_ID INTEGER,
registro_id integer references registro,
DESCRICAO VARCHAR(255),
datahora timestamptz,
velocidade float,
altitude float,
temperatura float,
umidade float,
localizacao GEOGRAPHY(POINT,4326)
);
	
INSERT INTO ponto_geo (inquilino_id, descricao, localizacao) VALUES (1, 'Town', ST_GeographyFromText('SRID=4326;POINT(-110 30)') );
INSERT INTO ponto_geo (inquilino_id, descricao,localizacao) VALUES (1, 'Forest', ST_GeographyFromText('SRID=4326;POINT(-109 29)') );
INSERT INTO ponto_geo (inquilino_id, descricao, localizacao) VALUES (1, 'London', ST_GeographyFromText('SRID=4326;POINT(0 49)') );
					
					
	
					
					
CREATE TABLE global_points (
id SERIAL PRIMARY KEY,
name VARCHAR(64),
location GEOGRAPHY(POINT,4326)
);
					
INSERT INTO global_points (name, location) VALUES ('Town', ST_GeographyFromText('SRID=4326;POINT(-110 30)') );
INSERT INTO global_points (name, location) VALUES ('Forest', ST_GeographyFromText('SRID=4326;POINT(-109 29)') );
INSERT INTO global_points (name, location) VALUES ('London', ST_GeographyFromText('SRID=4326;POINT(0 49)') );

					
SELECT * FROM global_points
					
drop TABLE global_points;
