/* bob / mot de passe : 123 */
insert into users (username, password, enabled) values ('bob', '$2a$10$/ns.CwZ9sdhQaVjw/bwBQeelnmTZTI19trLtyY/bjbIVUokAckX8y', true);
insert into authorities (username, authority) values ('bob', 'ADHERENT');


/* bob / mot de passe : 234 */
insert into users (username, password, enabled) values ('sara', '$2a$10$WPDbKLCRnV0UrkEs2IEtUejsZiicxt0/GhUcOkg2.UscjBi8tOmxa', true);
insert into authorities (username, authority) values ('sara', 'CONSEIL');


/* gege / mot de passe : 123 */
insert into users (username, password, enabled) values ('gege', '$2a$10$/ns.CwZ9sdhQaVjw/bwBQeelnmTZTI19trLtyY/bjbIVUokAckX8y', true);
insert into authorities (username, authority) values ('gege', 'BUREAU');


/* tete / mot de passe : 123 */
insert into users (username, password, enabled) values ('tete', '$2a$10$/ns.CwZ9sdhQaVjw/bwBQeelnmTZTI19trLtyY/bjbIVUokAckX8y', true);
insert into authorities (username, authority) values ('tete', 'ADMIN');
