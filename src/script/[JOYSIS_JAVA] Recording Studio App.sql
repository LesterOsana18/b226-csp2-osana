-- ===========================================
-- Recording App Database (Expanded Practice Dataset)
-- ===========================================

DROP DATABASE IF EXISTS recording_app_db;
CREATE DATABASE recording_app_db;
USE recording_app_db;

CREATE TABLE artists (
    id INT AUTO_INCREMENT PRIMARY KEY,
    artist_name VARCHAR(100) NOT NULL UNIQUE,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE albums (
    id INT AUTO_INCREMENT PRIMARY KEY,
    album_name VARCHAR(100) NOT NULL,
    year_released YEAR NOT NULL,
    artist_id INT NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT fk_album_artist
      FOREIGN KEY (artist_id)
      REFERENCES artists(id)
      ON DELETE RESTRICT
      ON UPDATE CASCADE
);

CREATE TABLE songs (
    id INT AUTO_INCREMENT PRIMARY KEY,
    title VARCHAR(150) NOT NULL,
    song_length VARCHAR(8) NOT NULL,
    genre VARCHAR(50) NOT NULL,
    album_id INT NOT NULL,
    is_archived TINYINT(1) NOT NULL DEFAULT 0,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
        ON UPDATE CURRENT_TIMESTAMP,
    CONSTRAINT fk_song_album
      FOREIGN KEY (album_id)
      REFERENCES albums(id)
      ON DELETE RESTRICT
      ON UPDATE CASCADE
);

CREATE TABLE users (
    id INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(50) NOT NULL UNIQUE,
    user_password VARCHAR(255) NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE playlists (
    id INT AUTO_INCREMENT PRIMARY KEY,
    playlist_name VARCHAR(100) NOT NULL,
    date_created DATE NOT NULL DEFAULT (CURRENT_DATE),
    user_id INT NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT fk_playlist_user
      FOREIGN KEY (user_id)
      REFERENCES users(id)
      ON DELETE RESTRICT
      ON UPDATE CASCADE
);

CREATE TABLE playlist_songs (
    id INT AUTO_INCREMENT PRIMARY KEY,
    playlist_id INT NOT NULL,
    song_id INT NOT NULL,
    CONSTRAINT fk_ps_playlist
      FOREIGN KEY (playlist_id)
      REFERENCES playlists(id)
      ON DELETE CASCADE
      ON UPDATE CASCADE,
    CONSTRAINT fk_ps_song
      FOREIGN KEY (song_id)
      REFERENCES songs(id)
      ON DELETE CASCADE
      ON UPDATE CASCADE
);

-- Artists
INSERT INTO artists (artist_name) VALUES
('Taylor Swift'),('Ed Sheeran'),('Bruno Mars'),('Adele'),
('The Weeknd'),('Coldplay'),('Imagine Dragons'),
('Olivia Rodrigo'),('Ben&Ben'),('Arthur Nery'),
('Moira Dela Torre'),('LANY'),('Maroon 5'),
('Billie Eilish'),('Paramore');

-- Albums
INSERT INTO albums (album_name, year_released, artist_id) VALUES
('1989',2014,1),('Midnights',2022,1),('Lover',2019,1),
('Divide',2017,2),('Equals',2021,2),
('24K Magic',2016,3),('Doo-Wops & Hooligans',2010,3),
('30',2021,4),('21',2011,4),
('After Hours',2020,5),('Dawn FM',2022,5),
('A Head Full of Dreams',2015,6),
('Evolve',2017,7),
('SOUR',2021,8),
('Pebble House Vol. 1',2021,9),
('Letters Never Sent',2022,10),
('Malaya',2018,11),
('gg bb xx',2021,12),
('V',2014,13),
('Hit Me Hard and Soft',2024,14),
('After Laughter',2017,15);

-- Sample songs (expand as desired)
INSERT INTO songs (title, song_length, genre, album_id) VALUES
('Blank Space','00:03:51','Pop',1),
('Style','00:03:51','Pop',1),
('Shake It Off','00:03:39','Pop',1),
('Anti-Hero','00:03:20','Pop',2),
('Cruel Summer','00:02:58','Pop',3),
('Shape of You','00:03:53','Pop',4),
('Perfect','00:04:23','Pop',4),
('Bad Habits','00:03:51','Pop',5),
('24K Magic','00:03:46','Funk Pop',6),
('Versace on the Floor','00:04:21','R&B',6),
('Grenade','00:03:42','Pop',7),
('Easy On Me','00:03:44','Soul',8),
('Rolling in the Deep','00:03:48','Soul',9),
('Blinding Lights','00:03:20','Synth-pop',10),
('Save Your Tears','00:03:35','Synth-pop',10),
('Take My Breath','00:03:40','Synth-pop',11),
('Adventure of a Lifetime','00:04:23','Pop Rock',12),
('Believer','00:03:24','Rock',13),
('drivers license','00:04:02','Pop',14),
('Leaves','00:05:10','OPM',15),
('Binhi','00:03:55','OPM',16),
('Malaya','00:04:30','OPM',17),
('ILYSB','00:03:34','Pop',18),
('Sugar','00:03:55','Pop',19),
('Birds of a Feather','00:03:30','Pop',20),
('Hard Times','00:03:02','Alternative',21);

INSERT INTO users(username, user_password) VALUES
('john_doe','password123'),
('jane_smith','qwerty456'),
('michael23','letmein789'),
('sarah01','musiclover'),
('admin','admin123'),
('lester','secret123'),
('swiftie13','taytay'),
('rockfan','dragon'),
('coffeecoder','java'),
('playlistking','music');

INSERT INTO playlists(playlist_name, date_created, user_id) VALUES
('Workout Mix','2026-07-01',1),
('Study Session','2026-07-02',2),
('Late Night Vibes','2026-07-03',3),
('Favorite OPM','2026-07-04',4),
('Road Trip','2026-07-05',5),
('Taylor Collection','2026-07-06',6),
('Weekend Hits','2026-07-07',7),
('Coffee Shop','2026-07-08',8),
('Rainy Day','2026-07-09',9),
('Top Picks','2026-07-10',10);

INSERT INTO playlist_songs(playlist_id, song_id) VALUES
(1,9),(1,18),(1,14),(2,6),(2,7),(2,20),
(3,15),(3,16),(3,23),(4,20),(4,21),(4,22),
(5,17),(5,24),(5,25),(6,1),(6,2),(6,4),(6,5),
(7,3),(7,8),(7,10),(8,12),(8,13),(8,26),
(9,11),(9,22),(10,6),(10,14),(10,24);