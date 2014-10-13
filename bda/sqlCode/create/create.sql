CREATE TABLE Familia (
	nom text,
	idFamilia INTEGER,

	PRIMARY KEY (idFamilia)
);

CREATE TABLE Subfamilia (
	nom text,
	idSubfamilia INTEGER,
	familia int,

	PRIMARY KEY (idSubfamilia, familia),
	FOREIGN KEY (familia) REFERENCES Familia(idFamilia)
);

CREATE TABLE Marca (
	nom text,
	PRIMARY KEY (nom)
);

CREATE TABLE Campanya (
	idCampanya INTEGER,
	nom text,
	data_fi text,
	data_inici text,
	numArticles  int,
	import real,

	CONSTRAINT positive_num_articles CHECK(numArticles >= 0),
	CONSTRAINT positive_import CHECK(import >= 0),

	PRIMARY KEY (idCampanya) 
);


CREATE TABLE Producte (
	idProducte INTEGER,
	nom text,
	color text,
	familia int,
	subfamilia int,

	PRIMARY KEY (idProducte),
	FOREIGN KEY (subfamilia, familia) REFERENCES Subfamilia(idSubfamilia, familia)
);

CREATE TABLE Article (
	idArticle INTEGER,
	producte int,
	campanya int,
	talla text,
	color text,
	stock int,
	preu real,
	IVA real,
	marca text,

	CONSTRAINT positive_stock CHECK(stock >= 0),
	CONSTRAINT positive_preu CHECK(preu >= 0),
	CONSTRAINT positive_IVA CHECK(IVA >= 0),

	PRIMARY KEY (idArticle),
	FOREIGN KEY (marca) REFERENCES Marca(nom),
	FOREIGN KEY (producte) REFERENCES Producte(idProducte),
	FOREIGN KEY (campanya) REFERENCES Campanya(idCampanya)
);

CREATE TABLE ArticleCampanya (
	idArticle int,
	idCampanya int,
	

	PRIMARY KEY (idArticle),
	FOREIGN KEY (idArticle) REFERENCES Article(idArticle),
	FOREIGN KEY (idCampanya) REFERENCES Campanya (idCampanya)
);

CREATE TABLE Usuari (
	nom text,
	contrasenya text,
	direccio text,
	
	PRIMARY KEY (nom)
);

CREATE TABLE UsuariArticleCampanya (
	nom text,
	idArticle int,
	unitatsComprades int,
	preuTotal real,
	impostos real,
	campanya int,

	CONSTRAINT positive_unitatsComprades CHECK(unitatsComprades >= 1),
	CONSTRAINT positive_preuTotal CHECK(preuTotal >= 0),
	CONSTRAINT positive_impostos CHECK(impostos >= 0),

	
	PRIMARY KEY (nom, idArticle, campanya),
	FOREIGN KEY (nom) REFERENCES Usuari (nom),
	FOREIGN KEY (campanya) REFERENCES Campanya (idCampanya),
	FOREIGN KEY (idArticle) REFERENCES Article (idArticle)
);
