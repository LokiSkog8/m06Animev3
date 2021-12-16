
CREATE TABLE usser(
userid uuid NOT NULL DEFAULT gen_random_uuid() PRIMARY KEY,
username text not null,
password text not null,
role text,
enabled boolean DEFAULT true
);
CREATE EXTENSION IF NOT EXISTS pgcrypto;
INSERT INTO usser (username, password) VALUES ('user', crypt('pass', gen_salt('bf')));

CREATE TABLE favorite(
userid uuid REFERENCES usser(userid) ON DELETE CASCADE,
animeid uuid REFERENCES anime(animeid) ON DELETE CASCADE,
PRIMARY KEY(userid, animeid)
);

INSERT INTO favorite values(
(SELECT userid FROM usser WHERE username = 'user'),(SELECT animeid from anime WHERE title = 'Mario Otaku'));

