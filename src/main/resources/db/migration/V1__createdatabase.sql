CREATE TABLE author(
authorid uuid NOT NULL DEFAULT gen_random_uuid() PRIMARY KEY,
name text,
imageurl text
);

CREATE TABLE anime(
animeid uuid NOT NULL DEFAULT gen_random_uuid() PRIMARY KEY,
title text,
synopsis text,
genre text,
yeare numeric,
imageurl text
);

CREATE TABLE anime_author(
animeid uuid REFERENCES anime(animeid) ON DELETE CASCADE,
authorid uuid REFERENCES author(authorid) ON DELETE CASCADE,
PRIMARY KEY (animeid, authorid)
);

CREATE TABLE genre(
genreid uuid NOT NULL DEFAULT gen_random_uuid() PRIMARY KEY,
label text,
imageurl text
);

CREATE TABLE anime_genre(
animeid uuid REFERENCES anime(animeid) ON DELETE CASCADE,
genreid uuid REFERENCES genre(genreid) ON DELETE CASCADE,
PRIMARY KEY(animeid, genreid)
);