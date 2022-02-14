INSERT INTO anime(title, synopsis, yeare, imageurl) VALUES
    ('Mario Otaku','La vida del Mario',1990,'movie1.jpg'),
    ('Doragon Arexu','Muahahahaha',1989,'movie2.jpg'),
    ('The Plains of Jordi','The Trilogy',2020,'movie3.jpg'),
    ('One Piece','Porque si',1997,'movie4.jpg');

INSERT INTO author(name, imageurl) VALUES
    ('Autor One','actor1.jpg'),
    ('Autor Two','actor2.jpg'),
    ('Autor Three','actor3.jpg'),
    ('Autor Four','actor4.jpg'),
    ('Autor Five','actor5.jpg');

INSERT INTO genre(label) VALUES
    ('Genre One'),
    ('Genre Two'),
    ('Genre Three');

INSERT INTO anime_author(animeid, authorid) VALUES
    ((SELECT animeid FROM anime WHERE title = 'Mario Otaku'),(SELECT authorid from author WHERE name = 'Autor One')),
     ((SELECT animeid FROM anime WHERE title = 'The Plains of Jordi'),(SELECT authorid from author WHERE name = 'Autor Two'));



CREATE EXTENSION IF NOT EXISTS pgcrypto;
INSERT INTO usser (username, password) VALUES
    ('user', crypt('pass', gen_salt('bf')));


INSERT INTO favorite values
    ((SELECT userid FROM usser WHERE username = 'user'),(SELECT animeid from anime WHERE title = 'Mario Otaku'));


