use googledrive_database
go
CREATE PROCEDURE create_share @shareId VARCHAR(100), @id VARCHAR(100), @user VARCHAR(100)
AS
BEGIN
DECLARE @userId INT;
DECLARE @documentId INT;

SET @userId = (SELECT id FROM [user] WHERE [user].email = @user);
SET @documentId = (select id from document where drive_document_id = @id)

INSERT INTO share VALUES (@shareId, @userId, @documentId)
END