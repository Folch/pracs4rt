CREATE TABLE Botiga (
	id_botiga int,
	desc_botiga text,
	id_municipi int,
	desc_municipi text,
	id_vegueria int,
	desc_vegueria text,

	CONSTRAINT clau_principal_botiga PRIMARY KEY(id_botiga)
);
CREATE TABLE Edat (

	id_edat int,
	desc_edat text,

	CONSTRAINT clau_principal_edat PRIMARY KEY(id_edat)
);
CREATE TABLE Usuari (
	id_usuari int,
	desc_usuari text,
	id_sexe int,
	desc_sexe text,


	CONSTRAINT clau_principal_usuari PRIMARY KEY(id_usuari)
);

CREATE TABLE Pagament (
	id_pagament int,
	desc_pagament text,

	CONSTRAINT clau_principal_pagament PRIMARY KEY(id_pagament)
);

CREATE TABLE Campanya (
	id_campanya int,
	desc_campanya text,

	CONSTRAINT clau_principal_campanya PRIMARY KEY(id_campanya)
);

CREATE TABLE Talla(
	id_talla int,
	desc_talla text,

	CONSTRAINT clau_principal_talla PRIMARY KEY(id_talla)
);
CREATE TABLE Color(
	id_color int,
	desc_color text,

	CONSTRAINT clau_principal_color PRIMARY KEY(id_color)
);
CREATE TABLE Familia(
	id_familia int,
	desc_familia text,
	id_subfamilia int,
	desc_subfamilia text,

	CONSTRAINT clau_principal_familia PRIMARY KEY(id_subfamilia)
);
CREATE TABLE Marca(
	id_marca int,
	desc_marca text,

	CONSTRAINT clau_principal_marca PRIMARY KEY(id_marca)
);
CREATE TABLE Producte (
	id_producte int,
	desc_producte text,

	CONSTRAINT clau_principal_producte PRIMARY KEY(id_producte)
);

CREATE TABLE Data (
	id_any int,
	desc_any text,
	id_mes int,
	desc_mes text,
	id_dia int,
	desc_dia text,

	CONSTRAINT clau_principal_data PRIMARY KEY(id_dia)

);

CREATE TABLE Fets (
	num_productes int,
	num_import real,
	
	id_usuari int,
	id_botiga int,
	id_campanya int,
	id_producte int,
	id_subfamilia int,
	id_data int,
	id_marca int,
	id_color int,
	id_talla int,
	id_pagament int,

	
	CONSTRAINT clau_foranea_fets_usuari FOREIGN KEY (id_usuari) REFERENCES usuari(id_usuari),
	CONSTRAINT clau_foranea_fets_botiga FOREIGN KEY (id_botiga) REFERENCES botiga(id_botiga),
	CONSTRAINT clau_foranea_fets_campanya FOREIGN KEY (id_campanya) REFERENCES campanya(id_campanya),
	CONSTRAINT clau_foranea_fets_producte FOREIGN KEY (id_producte) REFERENCES producte(id_producte),
	CONSTRAINT clau_foranea_fets_subfamilia FOREIGN KEY (id_subfamilia) REFERENCES familia(id_subfamilia),
	CONSTRAINT clau_foranea_fets_marca FOREIGN KEY (id_marca) REFERENCES marca(id_marca),
	CONSTRAINT clau_foranea_fets_color FOREIGN KEY (id_color) REFERENCES color(id_color),
	CONSTRAINT clau_foranea_fets_talla FOREIGN KEY (id_talla) REFERENCES talla(id_talla),
	CONSTRAINT clau_foranea_fets_pagament FOREIGN KEY (id_pagament) REFERENCES pagament(id_pagament),
	CONSTRAINT clau_foranea_fets_data FOREIGN KEY (id_data) REFERENCES data(id_dia)

);





