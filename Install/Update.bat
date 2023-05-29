@echo OFF
:inicio
cls

@echo Programa de atualizacao do Bicho Feliz
@echo. 
@echo.
@echo.
@echo.
set choice=
set /p choice=Deseja atualizar a versao do software? (S/N)
if '%choice%'=='s' goto atualizar
if '%choice%'=='S' goto atualizar
if '%choice%'=='n' goto sair
if '%choice%'=='N' goto sair
if '%choice%'=='N' (goto sair) else (goto inicio)
:atualizar
cls
@echo atualizando..
xcopy gestaopet.jar C:\gestaopet /y
xcopy gestaopet.exe C:\gestaopet /y



pause

:sair
cls
@echo saindo...
pause