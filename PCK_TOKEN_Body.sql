--------------------------------------------------------
--  File created - Monday-May-13-2024   
--------------------------------------------------------
--------------------------------------------------------
--  DDL for Package Body PCK_TOKEN
--------------------------------------------------------

  CREATE OR REPLACE EDITIONABLE PACKAGE BODY "CIS_SYS_NCB"."PCK_TOKEN" AS

  PROCEDURE PR_GET_TOKEN (p_out    OUT SYS_REFCURSOR) AS
  BEGIN
    -- TODO: Implementation required for PROCEDURE PCK_TOKEN.PR_GET_TOKEN
    OPEN P_OUT FOR
    SELECT * FROM SYS_TOKEN;

  END PR_GET_TOKEN;
END PCK_TOKEN;



/
