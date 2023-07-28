INSERT INTO songs (song_id, author, genre, name, link)
SELECT 1, 'Taylor Swift', 2, 'Blank Space', 'https://www.youtube.com/watch?v=e-ORhEE9VVg'
    WHERE
NOT EXISTS (
SELECT song_id, author, genre, name FROM songs WHERE song_id = 1
);

INSERT INTO songs (song_id, author, genre, name, link)
SELECT 2, 'Daft Punk', 1, 'One More Time','https://www.youtube.com/watch?v=FGBhQbmPwH8'
    WHERE
NOT EXISTS (
SELECT song_id, author, genre, name FROM songs WHERE song_id = 2
);

INSERT INTO songs (song_id, author, genre, name, link)
SELECT 3, 'Miles Davis', 3, 'So What', 'https://www.youtube.com/watch?v=ylXk1LBvIqU'
    WHERE
NOT EXISTS (
SELECT song_id, author, genre, name FROM songs WHERE song_id = 3
);

INSERT INTO songs (song_id, author, genre, name, link)
SELECT 4, 'Joni Mitchell', 4, 'Both Sides Now', 'https://www.youtube.com/watch?v=7cBf0olE9Yc'
    WHERE
NOT EXISTS (
SELECT song_id, author, genre, name FROM songs WHERE song_id = 4
);

INSERT INTO songs (song_id, author, genre, name, link)
SELECT 5, 'Beethoven', 5, 'Moonlight Sonata', 'https://www.youtube.com/watch?v=4Tr0otuiQuU'
    WHERE
NOT EXISTS (
SELECT song_id, author, genre, name FROM songs WHERE song_id = 5
);

INSERT INTO songs (song_id, author, genre, name,link)
SELECT 6, 'The Beatles', 0, 'Hey Jude','https://www.youtube.com/watch?v=A_MjCqQoLLA'
    WHERE
NOT EXISTS (
SELECT song_id, author, genre, name FROM songs WHERE song_id = 6
);

INSERT INTO songs (song_id, author, genre, name, link)
SELECT 7, 'Kraftwerk', 1, 'The Model','https://www.youtube.com/watch?v=KFq2pU21cNU'
    WHERE
NOT EXISTS (
SELECT song_id, author, genre, name FROM songs WHERE song_id = 7
);

INSERT INTO songs (song_id, author, genre, name, link)
SELECT 8, 'Madonna', 2, 'Vogue', 'https://www.youtube.com/watch?v=GuJQSAiODqI'
    WHERE
NOT EXISTS (
SELECT song_id, author, genre, name FROM songs WHERE song_id = 8
);

INSERT INTO songs (song_id, author, genre, name, link)
SELECT 9, 'Louis Armstrong', 3, 'What a Wonderful World', 'https://www.youtube.com/watch?v=e1FN047_LT0'
    WHERE
NOT EXISTS (
SELECT song_id, author, genre, name FROM songs WHERE song_id = 9
);

INSERT INTO songs (song_id, author, genre, name, link)
SELECT 10, 'AC/DC', 0, 'Highway to Hell', 'https://www.youtube.com/watch?v=l482T0yNkeo'
    WHERE
NOT EXISTS (
SELECT song_id, author, genre, name FROM songs WHERE song_id = 10
);