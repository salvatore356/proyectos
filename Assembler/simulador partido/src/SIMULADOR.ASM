
.MODEL Small

.STACK 100h

.DATA
	
	 ENT DB 10,13,"$"    ;permite el enter
  LINEA1 DB 'M E N U  D E  O P C I O N E S','$'
  LINEA2 DB '=======  ===  ===============','$'
  LINEA3 DB 'CASA DE APUESTAS','$'
  LINEA4 DB '"B W I N"','$'
  LINEA5 DB '1.- REALIZAR APUESTA','$'
  LINEA7 DB '2.- SALIR DEL SISTEMA','$'
  LINEA8 DB 'Elija opcion: ','$'
  
  MENU DB ? ;VARIABLE QUE LLEVA LA OPCION DEL MENU QUE SE ELIGE
  INCORREC DB 'Esa no es una opcion,por favor vuelva a intentar..$' 
  CONTINUAR DB 'Preione ENTER para continuar..',175,"$"
  
	
	
	ME1 db '          ','$'
	ME2 db '          ','$'
	FECHA DB '                ','$'
	EJEMPLO DB '  Ingrese la fecha y hora de la forma dd/mm/aaaa,hh:min: ','$'
		
	MSG1 db '  Ingrese el ranking del equipo 1: ','$'
	MSG2 db '  Ingrese el ranking del equipo 2: ','$'
	MSG db '  Los equipos deben estar entre el ranking 1-5','$'
	MSGa db '  Formacion no valida intente de nuevo','$'
	MSG1a db '  Ingrese la formacion del equipo 1: ','$'
	MSG2a db '  Ingrese la formacion del equipo 2: ','$'
	MSGEQUIPO1 db '  Ingrese el nombre del equipo 1 max (10): ','$'
	MSGEQUIPO2 db '  Ingrese el nombre del equipo 2 max (10): ','$'
	
	SELECCIONE1 db ' Para seleccionar el ranking de su equipo seleccione:                              ','$'
	SELECCIONE db '  1-Ranking 1, 2-Ranking 2, 3-Ranking 3, 4-Ranking 4, 5-Ranking 5','$'
	
	MEDIOTIEMP db ' MEDIO TIEMPO ','$'
	FINAL db ' FINAL DEL PARTIDO ','$'
	GANO1 db ' GANO EL EQUIPO 1 ','$'
	GANO2 db ' GANO EL EQUIPO 2 ','$'
	EMPATE db 'EQUIPOS EMPATADOS ','$'
	
	FORMACION db '  Formaciones disponibles (1)4-4-2, (2)4-3-3, (3)5-4-1','$'
	golequipo1 db ' Gol equipo 1   ','$'
	golequipo2 db ' Gol equipo 2   ','$'
	pcompletado db ' Pase Completado ','$'
	
	gol1 db ?  ;probabilidad de hacer gol equipo1
	pase1 db ? ;probabilidad de completar pase
	contra1 db ? ;probabilidad a favor de que le hagan gol
	paseh1 db 0
	golh1 db 0
	
	gol2 db ?  
	pase2 db ?
	contra2 db ?
	pases db 0 ;este lleva la cuenta de las jugadas en el partido
	paseh2 db 0
	golh2 db 0

	id db ?
	gol db ?
	pase db ?
	favor db ?	
	
	alineacion1 db ?
	alineacion2 db ?
	
	vrandom db ?
	
	a DB ?
	b DB ?
	
	
	PANT db '  |**************************************************************************|','$'
	PANT2 db '  ____________________________________________________________________________','$'
	RK0  db '  |  Ranking      |Probabilidad | Probabilidad | Probabilidad | Probabilidad |','$'
	RK1  db '  |  Internacional|    Ganar    |    Anotar    | Encajar Gol  | Pase Comple. |','$'
	RK2  db '  |  (1) 1-10     |     75      |     60       |     20       |     60       |','$'
	RK3  db '  |  (2) 11-20    |     60      |     50       |     30       |     50       |','$'
	RK4  db '  |  (3) 21-30    |     50      |     40       |     40       |     40       |','$'
	RK5  db '  |  (4) 31-40    |     40      |     30       |     45       |     35       |','$'
	RK6  db '  |  (5) 41-50    |     30      |     25       |     50       |     30       |','$'
	

	   
drawRect Macro X1,Y1,X2,Y2

	Push Y2   ;bp+10
    Push X2   ;bp+8
    Push Y1   ;bp+6
    Push X1   ;bp+4
    call auxDrawRect
   endM  
      
drawRect2 Macro X1,Y1,X2,Y2

	Push Y2   ;bp+10
    Push X2   ;bp+8
    Push Y1   ;bp+6
    Push X1   ;bp+4
    call auxDrawRect2
    
endM 
   
gotoXY  Macro X,Y

	Mov DL,X  ;Número de Columna -Max 79-
    Mov DH,Y  ;Número de Fila -Max 24-
    call auxGotoXY

endM 
    
putChar Macro caracter

	Mov DL,caracter
	call auxPutChar
	
endM
    
sumar Macro variable
	
	Mov al,variable
   	add al,1
   	mov variable,al
   
endM
    
drawVLine Macro X1,Y1,long,Ini,Med,Fin

	Push Fin  ;bp+14   ;Codigo Ascii
    Push Med  ;bp+12   ;Codigo Ascii
    Push Ini  ;bp+10   ;Codigo Ascii
    Push long ;bp+8
    Push Y1   ;bp+6
    Push X1   ;bp+4
    call auxDrawVLine

endM
    
drawHLine Macro X1,Y1,long,Ini,Med,Fin
	
	Push Fin  ;bp+14   ;Codigo Ascii
    Push Med  ;bp+12   ;Codigo Ascii
    Push Ini  ;bp+10   ;Codigo Ascii
    Push Long ;bp+8
    Push Y1   ;bp+6
    Push X1   ;bp+4
    call auxDrawHLine

endM

Read Macro var

	Lea BX,var
    call auxLeer

endM   

getChar Macro variable

	call auxGetChar
    mov variable,AL

endM
    
       
write Macro  cadena

	Lea DX,cadena
    call auxEscribir

endM

limpion db '          ','$'
limpiof db '                ','$'
.CODE
.486 ;me sirve para trabajar con numeros grandes

	Mov Ax,@Data
   	Mov DS,AX
 
 	iniciarotra:
	mov paseh1,0
	mov golh1,0
	mov paseh2,0
	mov golh2,0
	mov al,limpion
	mov ME1,al
	mov ME2,al
	mov al,limpiof
	mov FECHA,al
	
 	call clrscr ;LIMPIA PANTALLA  	
   	call menuin
   	
   	despuesmenu:

	call pantini ;CREA LA PANTALLA DE INICIO
	
	datosequipo1:
		call equipo1 ;ASIGNA LAS PROBABILIDADES DE LOS EQUIPOS
			
	datosequipo2:
		call equipo2
	
		
	cambiolosdatos:
    	call cambiardatos ;MODIFICA EL ID Y LAS PROBABILIDADES USADAS LA PRIMERA VEZ ASIGNA LOS VALORES A EQUIPO1


	otra:	;dibuja la cancha
  	
		call clrscr ;LIMPIA PANTALLA
      	drawRect2 32,9,42,14
  		drawRect 2,2,75,22
		drawVLine 37,2,19,205,179,205
		drawVLine 13,7,7,191,179,217 ;dibuja linea vertical
		drawVLine 64,7,7,218,179,192 ;218,217,191, 192
		drawHLine 2,7,10,186,196,191  ;196,186,191
		drawHLine 2,15,10,186,196,217
		drawHLine 64,15,10,192,196,186
		drawHLine 64,7,10,218,196,186
		call alinequipo1  ;CREA LA FORMACION DE LOS EQUIPOS
		call alinequipo2
		gotoXY 32,1
    	write fecha
	
		sumar pases      ;PARA LLEVAR EL CONTROL DE LAS JUGADAS
		call mediopartido
		call finpartido
		call ppase1 ;muestro pase 1	
		call incrementarP
				
		sumar pases
		call mediopartido
		call finpartido
		call obtenern ; OBTIENE EL ALEATORIO
		cmp vrandom, 99   ;COMPARA VRANDOM CON 99
			jbe cambiolosdatos ;SI ES MENOR O IGUAL VA A CAMBIOLOSDATOS
			call ppase2 ;muestro pase 2
			call incrementarP
	
		sumar pases
		call finpartido
		call mediopartido
		call obtenern
		cmp vrandom, 99   ;vrandom ==99?
			jbe cambiolosdatos ;si menor goto cambiolosdatos
			call ppase3 ;muestro pase3
			call incrementarP
	
		sumar pases
		call finpartido
		call mediopartido
		call obtenern
		cmp vrandom, 99   ;vrandom ==99?
			jbe cambiolosdatos ;si menor goto cambiolosdatos
			call ppase4 ;muestro pase 4
			call incrementarP
	
		sumar pases
		call finpartido
		call mediopartido
		call obtenerngol
		cmp vrandom, 149   ;vrandom ==149?
			jbe cambiolosdatos ;si menor goto cambiolosdatos
			call pgol ;muestro el gol
			call incrementarG
			jmp cambiolosdatos

	salir:
 		write ent
 		MOV AH,01H    ;PAUSA EL PROGRAMA
   		INT 21H

   		Mov Ah,4ch ;CIERRA EL PROGRAMA
   		int 21h

;PROCEDIMIENTOS UTILIZADOS

PROC pantini ;CREA LA PANDATALLA DE INICIO

   pantallanueva:
   call clrscr
 

   
   
   LEA DX,ENT  ;IMPRIMO ENTER
   MOV AH,09H
   INT 21H
   
   
   
   LEA DX,ENT  ;IMPRIMO ENTER
   MOV AH,09H
   INT 21H
   
   LEA DX,RK0    ;IMPRIME MENSAJE
   MOV AH,09H
   INT 21H
   
   LEA DX,ENT  
   MOV AH,09H
   INT 21H
         
   LEA DX,RK1    ;IMPRIME MENSAJE
   MOV AH,09H
   INT 21H
   
   LEA DX,ENT  
   MOV AH,09H
   INT 21H
   
   LEA DX,PANT2
   MOV AH,09H
   INT 21H
   
   LEA DX,ENT  
   MOV AH,09H
   INT 21H  
   
   LEA DX,RK2    ;IMPRIME MENSAJE
   MOV AH,09H
   INT 21H
   
   LEA DX,ENT  
   MOV AH,09H
   INT 21H
   
   
   
   LEA DX,ENT  
   MOV AH,09H
   INT 21H
   
   LEA DX,RK3  
   MOV AH,09H
   INT 21H
   
   LEA DX,ENT  
   MOV AH,09H
   INT 21H
   
   
   
   LEA DX,ENT  
   MOV AH,09H
   INT 21H
   
   LEA DX,RK4  ;IMPRIME MENSAJE
   MOV AH,09H
   INT 21H
   
   LEA DX,ENT
   MOV AH,09H
   INT 21H
   
   
   
   LEA DX,ENT  
   MOV AH,09H
   INT 21H
   
   LEA DX,RK5  ;IMPRIME MENSAJE
   MOV AH,09H
   INT 21H
   
   LEA DX,ENT  
   MOV AH,09H
   INT 21H
   
   
   
   LEA DX,ENT  
   MOV AH,09H
   INT 21H
   
   LEA DX,RK6  ;IMPRIME MENSAJE
   MOV AH,09H
   INT 21H
   
     
   
   
   
   
   drawRect2 2,1,77,14
   
   
   
   drawVLine 47,2,10,179,179,179
   drawVLine 32,2,10,179,179,179
   drawVLine 18,2,10,179,179,179
   drawVLine 62,2,10,179,179,179
   
   
   LEA DX,ENT  
   MOV AH,09H
   INT 21H
   
   
   
   LEA DX,ENT  
   MOV AH,09H
   INT 21H
   
   LEA DX,EJEMPLO    ;MUESTRA MENSAJE DE AYUDA PARA SELECCIONAR LA FECHA
   MOV AH,09H
   INT 21H
   Read FECHA	;LEE LA FECHA Y HORA DEL PARTIDO
   
   gotoxy 1,15
   
   LEA DX,SELECCIONE1    ;IMPRIME MENSAJE AYUDA LINEA1
   MOV AH,09H
   INT 21H
   
   
   
   LEA DX,ENT  ;IMPRIMO ENTER
   MOV AH,09H
   INT 21H 
   
   LEA DX,SELECCIONE    ;IMPRIME MENSAJE AYUDA LINEA2
   MOV AH,09H
   INT 21H
  
       
   LEA DX,ENT  
   MOV AH,09H
   INT 21H
        
   LEA DX,ENT  
   MOV AH,09H
   INT 21H

   call obtener ;PARA OBTENER LOS DATOS (RANKING EQUIPO, ALINEACION, NOMBRE, ETC)
    ret
pantini endp

 
  
  
PROC obtener  ;PROCESO PARA OBTENER LOS DATOS DE LOS EQUIPO
 
   
   
   gotoxy 5,18
 
   LEA DX,MSGEQUIPO1    ;MUESTRA MENSAJE DE NOMBRE DE EQUIPO
   MOV AH,09H
   INT 21H
   Read ME1				;LEE EL NOMBRE DEL EQUIPO1
   
   LEA DX,ENT 
   MOV AH,09H
   INT 21H 
 
 	gotoxy 5,19  
 
   LEA DX,MSG1    ;PIDE EL RANKING DEL EQUIPO 1
   MOV AH,09H
   INT 21H
      
   MOV AH,01H   ;ALMACENA EL RANKING EN UNA VARIABLE
   INT 21H		;SERVICIO PARA LEER UN CARACTER Y LO GUARDA EN AL
   MOV CH,AL	;movemos a ch el caracter
   SUB CH,30H	;lo convertimos en decimal
   mov a,ch
   
   
   LEA DX,ENT  
   MOV AH,09H
   INT 21H
   
   cmp a,0				;VERIFICA QUE LOS RANKINGS DE LOS EQUIPOS SEAN VALIDOS
     jbe advertencia
   cmp a,6
     jge advertencia
      
 
 	gotoxy 5,20  
 
   LEA DX,MSGEQUIPO2    ;MUESTRA MENSAKE DE EQUIPO2
   MOV AH,09H
   INT 21H
   Read ME2				;LEE EL NOMBRE DEL EQUIPO2
   
   LEA DX,ENT  
   MOV AH,09H
   INT 21H 
   
   gotoxy 5,21
   
   LEA DX,MSG2	;PIDE EL RANKING DEL EQUIPO2
   MOV AH,09H
   INT 21H
   
   MOV AH,01H     ;ALMACENA EL RANKING EN LA VARIABLE B
   INT 21H
   MOV CL,AL
   SUB CL,30H
   mov b, cl
      
   LEA DX,ENT  
   MOV AH,09H
   INT 21H
   
   
   cmp b,0
     jbe advertencia
   cmp b,6
     jge advertencia
  	 call obtenerformacion ;SE OBTIENE LA FORMACION DE AMBOS EQUIPOS
  	 jmp datosequipo1
  	  
 advertencia:
   	LEA DX,MSG  ;Imprimimos el msg de advertencia
   	MOV AH,09H
   	INT 21H
   
   	MOV AH,01H    ;COMO UN SYSTEM PAUSE
   	INT 21H
   	jmp pantallanueva 
   
   	RET
   
obtener endp

Proc clrScr      ;este procedimiento borra la pantalla
   
	MOV AX, 0300H ; Petición AH=06, AL 00 pantalla completa
    MOV BH,111b  ; atributo blanco (7) sobre azul (1)
    MOV CX, 0000H ; esquina superior izq fila: columna  Desde 00,00
    MOV DX, 184FH ; esquina inferior der fila: columna  Hasta 24, 79 (pantalla completa)
    INT 10H
    Mov AX,03h
    int 10h
     
   ret 
   
  clrScr Endp
  
Proc equipo1  ;ASIGNA LOS DATOS DEL EQUIPO 1
	
	cmp a,1
	   je datos1
	cmp a,2
	   je datos2
	cmp a,3
	   je datos3
	cmp a,4
	   je datos4
	cmp a,5
	   je datos5
	
    datos1:
		mov gol1,60
		mov contra1,20
		mov  pase1,60
		jmp datosequipo2
	
	datos2:
		mov gol1,50
		mov  contra1,30
		mov  pase1,50
		jmp datosequipo2
	
	datos3:
		mov gol1,40
		mov  contra1,40
		mov  pase1,40
		jmp datosequipo2
	
	datos4:
		mov gol1,30
		mov  contra1,45
		mov  pase1,35
		jmp datosequipo2
	
	datos5:
		mov gol1,25
		mov  contra1,50
		mov  pase1,30
		jmp datosequipo2

    RET
    
equipo1 Endp


Proc equipo2 ;DEFINE LOS DATOS DEL EQUIPO 2

	cmp b,1
		je datos6
	cmp b,2
		je datos7
	cmp b,3
		je datos8
	cmp b,4
		je datos9
	cmp b,5
		je datos10
	
	datos6:
		mov gol2,60
		mov  contra2,20
		mov  pase2,60
		jmp otra
	
	datos7:
		mov gol2,50
		mov  contra2,30
		mov  pase2,50
		jmp otra
	
	datos8:
		mov gol2,40
		mov  contra2,40
		mov  pase2,40
		jmp otra
	
	datos9:
		mov gol2,30
		mov  contra2,45
		mov  pase2,35
		jmp otra
	
	datos10:
		mov gol2,25
		mov  contra2,50
		mov  pase2,30
		jmp otra
	RET
	
equipo2 Endp

Proc auxDrawRect ;AUXILIAR PARA DIBUJAR EL RECTANGULO
      
      push bp
      mov bp,sp  
      Mov CL,[BP+4]
      gotoXY CL,[BP+6]
      putChar 201
      gotoXY CL,[BP+10]
      putChar 200
      
      Do:
      	inc cl
      	cmp CL,[BP+8]
      	jae While
      	gotoXY CL,[BP+6];AL
      	putChar 205
      	gotoXY CL,[BP+10]
      	putChar 205
      	jmp Do
     while:
      	gotoXY CL,[BP+6]
      	putchar 187
      	gotoXY CL,[BP+10]
      	putChar 188
       	Mov CL,[BP+6]
             
      Do2:
      	inc cl
       	cmp CL,[BP+10]
       	jae While2
       	gotoXY [BP+4],Cl
       	putChar 186
       	gotoXY [BP+8],Cl
       	putChar 186
       	jmp Do2
     
     while2:
      	Mov CX,[BP+10]
      	inc cx
        Pop BP  
	
      ret 
      
endP

Proc auxGotoXY
	
	Mov BX,0
    Mov AH,02h ;Servicio para Posicionar el Cursor
    Int 10h    ;Interrupcion de Video 
    
    ret
    
auxGotoXY endP
   
Proc auxDrawRect2
	push bp
    mov bp,sp  
    Mov CL,[BP+4]
    gotoXY CL,[BP+6]
    putChar 218
    gotoXY CL,[BP+10]
    putChar 192
      
    Do3:
    	inc cl
       	cmp CL,[BP+8]
       	jae While3
       	gotoXY CL,[BP+6];AL
       	putChar 196
       	gotoXY CL,[BP+10]
       	putChar 196
       	jmp Do3
       	
    while3:
       gotoXY CL,[BP+6]
       putchar 191
       gotoXY CL,[BP+10]
       putChar 217
       
       Mov CL,[BP+6]
             
    Do4:
       inc cl
       cmp CL,[BP+10]
       jae While4
       gotoXY [BP+4],Cl
       putChar 179
       gotoXY [BP+8],Cl
       putChar 179
       jmp Do4
     
     while4:
       Mov CX,[BP+10]
       inc cx
       Pop BP  
       
     ret 
     
endP

Proc auxPutChar
	
	push AX
    Mov AH,02h  ;servicio para escribir un caracter en la pantalla
    int 21h
    pop AX
    
    ret
    
auxPutChar EndP  

Proc auxDrawVLine 
      
    push BP
    Mov BP,SP
    Mov CH,[bp+6]
    Add CH,[bp+8]
    Mov CL,[bp+6]    ;Mov CL,Y1
    gotoXY [bp+4],CL ;goto X1,Y1 
    Mov BL,[bp+10]
    putChar BL  ; putChar ini

    Do6:
		inc cl
        gotoXY [bp+4],CL
        putChar [bp+12] ; putChar med
        cmp cl,ch ; ; if(y1<=y2)
        jle Do6         ; true
        gotoXY [bp+4],CL  ;gotoXY X1,Y1
        putChar [bp+14]          ;putChar fin
        Pop BP
    
    ret 14  
  
auxDrawVLine EndP

Proc auxDrawHLine 
      
	push BP
    Mov BP,SP
    Mov CH,[bp+4]  
    ADD CH,[bp+8]    ;x=x+logitud
    Mov CL,[bp+4]    ;Mov CL,X1
    gotoXY CL,[bp+6] ;goto X1,Y1 
    putChar [bp+10]  ; putChar ini
    
    Do5:
		inc cl
        putChar [bp+12] ; putChar med
        cmp cl,ch; ; if(x1<=x2)
        jle Do5         ; true
        gotoXY CL,[bp+6]  ;gotoXY X1,Y1
        putChar [bp+14]          ;putChar fin
        Pop BP
        
    ret    
  
auxDrawHLine EndP

Proc alinequipo1 ;ASIGNA LA ALINEACION DEL EQUIPO
	
	cmp alineacion1,1
		je formacion442
	cmp alineacion1,2
		je formacion433
	cmp alineacion1,3
		je formacion541
		
	formacion442:
		cmp pases,127
	  	jb frm442t  ;verifica si es mayor
	    gotoXY 72,11 ;el portero
        putChar 1
        gotoXY 56,6 ;los defensas
        putChar 1
        gotoXY 56,10
        putChar 1
        gotoXY 56,14
        putChar 1
        gotoXY 56,18
        putChar 1
        gotoXY 47,4 ;los medio campo
        putChar 1
        gotoXY 47,8 
        putChar 1           
        gotoXY 47,14 
        putChar 1           
        gotoXY 47,20 
        putChar 1   
        gotoXY 40,10 ;los delanteros 
        putChar 1
        gotoXY 40,14 
        putChar 1
      
        gotoXY 65,23
        WRITE ME1
        jmp fin
      
	frm442t:
		gotoXY 7,11 ;el portero
        putChar 1
        gotoXY 18,6 ;los defensas
        putChar 1
        gotoXY 18,10
        putChar 1
        gotoXY 18,14
        putChar 1
        gotoXY 18,18
        putChar 1
        gotoXY 25,4 ;los medio campo
        putChar 1
        gotoXY 25,8 
        putChar 1           
        gotoXY 25,14 
        putChar 1           
        gotoXY 25,20 
        putChar 1   
        gotoXY 32,10 ;los delanteros 
        putChar 1
        gotoXY 32,14 
        putChar 1
                  
        gotoXY 3,1
        WRITE ME1
        jmp fin  
               
      formacion433:
	  	cmp pases,127
	  	jb frm433t  ;verifica si es menor
	  	gotoXY 72,11 ;el portero
      	putChar 1
      	gotoXY 56,6 ;los defensas
      	putChar 1
      	gotoXY 56,10
      	putChar 1
      	gotoXY 56,14
      	putChar 1
      	gotoXY 56,18
      	putChar 1
      	gotoXY 47,12 ;los medio campo
      	putChar 1
      	gotoXY 47,8 
      	putChar 1           
      	gotoXY 47,16 
      	putChar 1           
      	gotoXY 40,8 ;los delanteros 
      	putChar 1   
      	gotoXY 40,12
      	putChar 1
      	gotoXY 40,16 
      	putChar 1
	  
	    gotoXY 65,23
     	WRITE ME1
	    jmp fin
            
      frm433t:
      	gotoXY 7,11 ;el portero
      	putChar 1
      	gotoXY 18,6 ;los defensas
      	putChar 1
      	gotoXY 18,10
      	putChar 1
      	gotoXY 18,14
      	putChar 1
      	gotoXY 18,18
      	putChar 1
      	gotoXY 25,12 ;los medio campo
      	putChar 1
      	gotoXY 25,8 
      	putChar 1           
      	gotoXY 25,16 
      	putChar 1           
      	gotoXY 32,8 ;los delanteros 
      	putChar 1   
      	gotoXY 32,12 
      	putChar 1
      	gotoXY 32,16 
      	putChar 1 
      
	    gotoXY 3,1
        WRITE ME1
        jmp fin           
                 
    
    formacion541:
 	  	cmp pases,127
	  	jb frm541t  ;verifica si es menor
	  	gotoXY 72,11 ;el portero
      	putChar 1
      	gotoXY 56,6 ;los defensas
      	putChar 1
      	gotoXY 56,9
      	putChar 1
      	gotoXY 56,12
      	putChar 1
      	gotoXY 56,15
      	putChar 1
      	gotoXY 56,18 
      	putChar 1
      	gotoXY 47,11 ;los medio campo
      	putChar 1           
      	gotoXY 47,14 
      	putChar 1           
      	gotoXY 47,8 
      	putChar 1   
      	gotoXY 47,17
      	putChar 1
	    gotoXY 40,12 ;los delanteros 
      	putChar 1
	        
      	gotoXY 65,23
      	WRITE ME1
      	jmp fin
      
      frm541t:
      	gotoXY 7,11 ;el portero
      	putChar 1
      	gotoXY 18,6 ;los defensas
      	putChar 1
      	gotoXY 18,9
      	putChar 1
      	gotoXY 18,12
      	putChar 1
      	gotoXY 18,15
      	putChar 1
      	gotoXY 18,18 
      	putChar 1
      	gotoXY 25,11 ;los medio campo
      	putChar 1           
      	gotoXY 25,14 
      	putChar 1           
      	gotoXY 25,8 
      	putChar 1   
      	gotoXY 25,17 
      	putChar 1
      	gotoXY 32,12;los delanteros  
      	putChar 1
                	
        gotoXY 3,1
      	WRITE ME1
      	jmp fin
       
    fin:
    
    	ret
    	
alinequipo1 endp
    
    
Proc alinequipo2 ;SELECCIONA LA ALINEACION DEL EQUIPO 2
	
	cmp alineacion2,1
		je formacion442a
	cmp alineacion2,2
		je formacion433a
	cmp alineacion2,3
		je formacion541a
		
	formacion442a:
		cmp pases,127
	  	jb frm442ta  ;verifica si es menor
	  	gotoXY 7,11 ;el portero
      	putChar 2
      	gotoXY 18,6 ;los defensas
      	putChar 2
      	gotoXY 18,10
      	putChar 2
      	gotoXY 18,14
      	putChar 2
      	gotoXY 18,18
      	putChar 2
      	gotoXY 25,4 ;los medio campo
      	putChar 2
      	gotoXY 25,8 
      	putChar 2           
      	gotoXY 25,14 
      	putChar 2           
      	gotoXY 25,20 
      	putChar 2   
      	gotoXY 32,10 ;los delanteros 
      	putChar 2
      	gotoXY 32,14 
      	putChar 2
      
      	gotoXY 3,1
      	WRITE ME2
	    jmp fin
	  
      
	frm442ta:
      	gotoXY 72,11 ;el portero
      	putChar 2
      	gotoXY 56,6 ;los defensas
      	putChar 2
      	gotoXY 56,10
      	putChar 2
      	gotoXY 56,14
      	putChar 2
      	gotoXY 56,18
      	putChar 2
      	gotoXY 47,4 ;los medio campo
      	putChar 2
      	gotoXY 47,8 
      	putChar 2           
      	gotoXY 47,14 
      	putChar 2           
      	gotoXY 47,20 
      	putChar 2   
      	gotoXY 40,10 ;los delanteros 
      	putChar 2
      	gotoXY 40,14 
      	putChar 2
	    
      	gotoXY 65,23
      	WRITE ME2
        jmp fina  
               
	formacion433a:
		cmp pases,127
	  	jb frm433ta  ;verifica si es menor
	  	gotoXY 7,11 ;el portero
      	putChar 2
      	gotoXY 18,6 ;los defensas
      	putChar 2
      	gotoXY 18,10
      	putChar 2
      	gotoXY 18,14
      	putChar 2
      	gotoXY 18,18
      	putChar 2
      	gotoXY 25,12 ;los medio campo
      	putChar 2
      	gotoXY 25,8 
      	putChar 2           
      	gotoXY 25,16 
      	putChar 2           
      	gotoXY 32,8 ;los delanteros 
      	putChar 2   
      	gotoXY 32,12 
      	putChar 2
      	gotoXY 32,16 
      	putChar 2
	      
      	gotoXY 3,1
      	WRITE ME2
        jmp fina
      
	frm433ta:
		gotoXY 72,11 ;el portero
      	putChar 2
      	gotoXY 56,6 ;los defensas
      	putChar 2
      	gotoXY 56,10
      	putChar 2
      	gotoXY 56,14
      	putChar 2
      	gotoXY 56,18
      	putChar 2
      	gotoXY 47,12 ;los medio campo
      	putChar 2
      	gotoXY 47,8 
      	putChar 2           
      	gotoXY 47,16 
      	putChar 2           
      	gotoXY 40,8 ;los delanteros 
      	putChar 2   
      	gotoXY 40,12
      	putChar 2
      	gotoXY 40,16 
      	putChar 2
      
       	gotoXY 65,23
      	WRITE ME2
        jmp fina           
        
	formacion541a:
		cmp pases,127
	  	jb frm541ta  ;verifica si es menor
	  	gotoXY 7,11 ;el portero
      	putChar 2
      	gotoXY 18,6 ;los defensas
      	putChar 2
      	gotoXY 18,9
      	putChar 2
      	gotoXY 18,12
      	putChar 2
      	gotoXY 18,15
      	putChar 2
      	gotoXY 18,18 
      	putChar 2
      	gotoXY 25,11 ;los medio campo
      	putChar 2           
      	gotoXY 25,14 
      	putChar 2           
      	gotoXY 25,8 
      	putChar 2   
      	gotoXY 25,17 
      	putChar 2
      	gotoXY 32,12;los delanteros  
      	putChar 2
	      
      	gotoXY 3,1
      	WRITE ME2
        jmp fina
      
	frm541ta:
		gotoXY 72,11 ;el portero
      	putChar 2
      	gotoXY 56,6 ;los defensas
      	putChar 2
      	gotoXY 56,9
      	putChar 2
      	gotoXY 56,12
      	putChar 2
      	gotoXY 56,15
      	putChar 2
      	gotoXY 56,18 
      	putChar 2
      	gotoXY 47,11 ;los medio campo
      	putChar 2           
      	gotoXY 47,14 
      	putChar 2           
      	gotoXY 47,8 
      	putChar 2   
      	gotoXY 47,17
      	putChar 2
      	gotoXY 40,12 ;los delanteros 
      	putChar 2
	      
      	gotoXY 65,23
      	WRITE ME2
		jmp fina
    
	fina:
	
    	ret
    	
alinequipo2 endp

PROC obtenerformacion ;OBTIENE LOS DATOS PARA LAS FORMACIONES
 
 
   	LEA DX,FORMACION    ;PARA MOSTRAR LAS FORMACIONES DISPONIBLES
   	MOV AH,09H
   	INT 21H
   
   	LEA DX,ENT  ;Imprimimos el enter
   	MOV AH,09H
   	INT 21H
 
 	gotoxy 5,23  
   	LEA DX,MSG1a    ;PARA MOSTAR EL MENSAJE DE PEDIR EL DATO1
   	MOV AH,09H
   	INT 21H
      
   	MOV AH,01H   ;PARA GUARDAR LA FORMACION DEL EQUIPO 1 EN ALINEACION 1
   	INT 21H		;SERVICIO PARA LEER UN CARACTER Y LO GUARDA EN AL
   	MOV CH,AL	;movemos a ch el caracter
   	SUB CH,30H	;lo convertimos en decimal
   	mov alineacion1,ch  
   	   	      
   	LEA DX,ENT
   	MOV AH,09H
   	INT 21H
   	
   	cmp alineacion1,0				;VERIFICA QUE LAS FORMACIONES SEAN VALIDAS
  		jbe advertencia2
  	cmp alineacion1,4
  		jge advertencia2
   	
   
   gotoxy 5,24  
   	LEA DX,MSG2a	;MOSTRAR MENSAJE DE FORMACION 2
   	MOV AH,09H
   	INT 21H
   
   	MOV AH,01H     ;OBTENEMOS FORMACION DEL EQUIPO 2
   	INT 21H
   	MOV CL,AL
   	SUB CL,30H
   	mov alineacion2, cl
      
   	LEA DX,ENT  
   	MOV AH,09H
   	INT 21H
   
 
  	cmp alineacion2,0
  		jbe advertencia2
  	cmp alineacion2,4
  		jge advertencia2
  	jmp datosequipo1
  	
  	  
	advertencia2:
   		LEA DX,MSGa  ;Imprimimos el msg de advertencia
   		MOV AH,09H
   		INT 21H
   
   		MOV AH,01H    ;COMO UN SYSTEM PAUSE
   		INT 21H
   	    jmp pantallanueva 
   	    
	RET 
	 
obtenerformacion endp


Proc ppase1 ;MOSTRAMOS EL PASE 1

	cmp id,1
		je pasea1
	cmp id,2
		je paseb1
				
	pasea1:
		cmp pases,127
	  	jbe pase1equipo1t1  ;verifica si es menor
	  	gotoXY 71,11 ;el portero
      	putChar 22 ;15,22
      	jmp finpase
      
	pase1equipo1t1:
		gotoXY 8,11
      	putChar 22
      	jmp finpase
      
	paseb1:
		cmp pases,127
	  	jbe pase1equipo2t1  ;brinca a pase 1 verifica si es menor
	  	gotoXY  8,11;el portero
      	putChar 22
      	jmp finpase
      
	pase1equipo2t1:
      	gotoXY 71,11
      	putChar 22
      	jmp finpase
	  
	finpase:
    	call pausa ;PAUSA LA PANTALLA POR UNOS INSTATENTES
      	
      	ret
      	
ppase1 endp
      
   
Proc ppase2 ;MUESTRA EL PASE 2 Y LA DIRECCION DE DONDE VIENE
		   
	cmp id,1
		je pasea2
	cmp id,2
		je paseb2
				
	pasea2:
	  	cmp pases,127
	  	jbe pase2equipo1t1
	  	gotoXY 55,9
      	putChar 22 
      	gotoXY 71,11 
	  	putChar 12
      	jmp finpase2
      
	pase2equipo1t1:
      	gotoXY 19,9
      	putChar 22
      	gotoXY 8,11
      	putChar 11
      	jmp finpase2
      
	paseb2:
	  	cmp pases,127
	  	jbe pase2equipo2t1  ;brinca a pase 1 verifica si es menor
	  	gotoXY  19,9
      	putChar 22
      	gotoXY 8,11
      	putChar 11
      	jmp finpase2
      
	pase2equipo2t1:
      	gotoXY 55,9
      	putChar 22
      	gotoXY 71,11 
	  	putChar 12
      	jmp finpase2
   
	finpase2:
      	gotoXY 3,23
      	LEA DX,pcompletado    ;MUESTRA MENSAJE INDICANDO QUE EL PASE FUE COMPLETADO
   	  	MOV AH,09H
      	INT 21H
      	call pausa
      	
      	ret
      	
ppase2 endp

Proc ppase3  ;MUESTRA EL PASE 3 Y DE DONDE VIENE
				        
	cmp id,1
		je pasea3
	cmp id,2
		je paseb3
				
	pasea3:
	  	cmp pases,127
	  	jbe pase3equipo1t1  ;verifica si es menor
	  	gotoXY  46,8
      	putChar 22 ;imprime el balon
      	gotoXY 55,9 ;el portero
	  	putChar 12 ;15,22, 7 espacio en blanco
      	jmp finpase3
      
	pase3equipo1t1:
      	gotoXY 26,8
      	putChar 22
      	gotoXY 19,9
      	putChar 11
      	jmp finpase3
	      
	paseb3:
	  	cmp pases,127
	  	jbe pase3equipo2t1
	  	gotoXY  26,8
      	putChar 22
      	gotoXY 19,9
      	putChar 11
      	jmp finpase3
      
	pase3equipo2t1:
      	gotoXY 46,8
      	putChar 22
      	gotoXY 55,9 ;el portero
	  	putChar 12 ;15,22, 7 espacio en blanco
      	jmp finpase3
	  
	finpase3:
      	gotoXY 3,23
      	LEA DX,pcompletado    ;MUESTRA MENSAJE DE PASE COMPLETADO
   	  	MOV AH,09H
      	INT 21H
      	call pausa ;GENERA UNA PAUSA POR UN INSTANTE DE TIEMPO
      	
      	ret
      	
ppase3 endp

Proc ppase4 ;MUESTRA EL PASE 4 Y DE DONDE VIENE
						       
	cmp id,1
		je pasea4
	cmp id,2
		je paseb4
				
	pasea4:
	  	cmp pases,127
	  	jbe pase4equipo1t1  ;verifica si es menor
	  	gotoXY  41,9
      	putChar 22 ;imprime el balon
      	gotoXY 46,8 ;el portero
	  	putChar 12 ;15,22, 7 espacio en blanco
      	jmp finpase4
	      
	pase4equipo1t1:
      	gotoXY 33,11,8
      	putChar 22
      	gotoXY 26,8
      	putChar 11
      	jmp finpase4
      
	paseb4:
	  	cmp pases,127
	  	jbe pase4equipo2t1  ;brinca a pase 1 verifica si es menor
	  	gotoXY  33,11;el portero
      	putChar 22
      	gotoXY 26,8
      	putChar 11
      	jmp finpase4
      
	pase4equipo2t1:
      	gotoXY 41,9
      	putChar 22
      	gotoXY 46,8 ;el portero
	  	putChar 12 ;15,22, 7 espacio en blanco
      	jmp finpase4
	  
	finpase4:
      	gotoXY 3,23
      	LEA DX,pcompletado    ;MUESTRA MENSAJE DE PASE COMPLETADO
   	  	MOV AH,09H
      	INT 21H
      	call pausa ;CREA UNA PAUSA POR UNOS INSTANTES
      	
      	ret
      	
ppase4 endp
      
Proc pgol ;MUESTRA EL GOL Y DE DONDE VIENE
		
	cmp id,1
		je pgola
	cmp id,2
		je pgolb
				
	pgola:
	  	cmp pases,127
	  	jbe pgolequipo1t1  ;verifica si es menor
	  	gotoXY  2,10
      	putChar 22 ;imprime el balon
      	gotoXY 41,9 ;el portero
	  	putChar 12 ;15,22, 7 espacio en blanco
	  
	  	gotoXY 3,23
	  	LEA DX,GOLEQUIPO1    ;MUESTRA MENSAJE DE GOL
   	  	MOV AH,09H
      	INT 21H
	    jmp fingol
      
	pgolequipo1t1:
      	gotoXY 75,8
      	putChar 22
      	gotoXY 33,11
      	putChar 11
      
      	gotoXY 3,23
      	LEA DX,GOLEQUIPO1    ;MUESTRA MENSAJE DE GOL
   	  	MOV AH,09H
      	INT 21H
      	jmp fingol
      
	pgolb:
	  	cmp pases,127
	  	jbe pgolequipo2t1  ;brinca a pase 1 verifica si es menor
	  	gotoXY  75,10;el portero
      	putChar 22
      	gotoXY 33,11
      	putChar 11
      
      	gotoXY 3,23
      	LEA DX,GOLEQUIPO2    ;MUESTRA MENSAJE DE GOL
   	  	MOV AH,09H
      	INT 21H
        jmp fingol
      
	pgolequipo2t1:
      	gotoXY 2,9
      	putChar 22
      	gotoXY 41,9 
	  	putChar 12 
		  
	  	gotoXY 3,23
	  	LEA DX,GOLEQUIPO2    ;PARA MOSTRAR LAS FORMACIONES DISPONIBLES
   	  	MOV AH,09H
      	INT 21H
	   	jmp fingol
     
	fingol:
		call pausa
      	
      	ret
      	
pgol endp

proc generaraleatorio ;GENERA EL NUMERO ALEATORIO
	
	Mov ah,2Ch
	Int 21h
	mov vrandom,dl
	add vrandom,10
	
	ret
	
generaraleatorio endp

proc cambiardatos ;INTERCAMBIA LOS DATOS UTILIZADOS PARA LOS CALCULOS DE PASES Y GOL
  
	cmp id,1
		je traerdatos2
    cmp id,2
  		je traerdatos1
  
	traerdatos1:
		mov id,1  
  		mov al,gol1
  		mov gol,al
  		mov al, pase1
  		mov pase, al
  		mov al, contra2
  		mov favor,al
 		jmp fincambiardatos
  
	traerdatos2:
		mov id,2 
  		mov al,gol2
  		mov gol, al
  		mov al, pase2
  		mov pase, al
  		mov al, contra1
  		mov favor, al  
 		
 	fincambiardatos:
 	
 	ret

cambiardatos endp
      
proc obtenern ;RANDOM(100) + PROBABILIDADDEPASE
	
	call generaraleatorio ;genero el aleatorio
	mov al, vrandom
	add al,pase     ;aleatorio = aleatorio + probabilidad de completar pase
	mov vrandom, al
	
	ret
	
obtenern endp

proc obtenerngol
	
	call generaraleatorio ;genero el aleatorio
	mov al, vrandom
	add al,gol     ;aleatorio = aleatorio + probabilidad de completar pase
	add al,favor
	mov vrandom, al
	
	ret

obtenerngol endp

proc incrementarP ;INCREMENTA LOS PASES POR EQUIPO
  
	cmp id,1
  		je spa
  		sumar paseh2
  		jmp dase

	spa:
  		sumar paseh1
  
  	dase:
  	
  	ret  
  	
incrementarP endP
 
proc incrementarG  ;INCREMENTA LOS GOLES POR EQUIPO
  
	cmp id,1
  		je sps
  		sumar golh2
  		jmp dae
  
	sps:
  		sumar golh1
  	dae:
  	
  	ret  
  	
incrementarG endP
  
proc mediopartido ;DA UN RESUMEN DEL JUEGO A MEDIO TIEMPO

	cmp pases, 127
  		je auxfinp
  		jmp finfinp
   	
  	auxfinp:
  		call clrscr
		drawRect 15,6,60,20
  		drawVLine 37,6,13,205,179,205
  		drawHLine 15,8,44,186,196,186  	
  	 
 		gotoXY 1,1
 		LEA DX,PANT
   		MOV AH,09H
    	INT 21H
    
    	gotoXY 1,22
 		LEA DX,PANT
   		MOV AH,09H
	    INT 21H
 
 		gotoXY 16,2
 		LEA DX,MEDIOTIEMP 
   		MOV AH,09H
    	INT 21H
 
  		gotoXY 16,7
		LEA DX,ME1 
   		MOV AH,09H
    	INT 21H
    
    	gotoXY 38,7
		LEA DX,ME2    
   		MOV AH,09H
    	INT 21H
        
    	gotoXY 16,11
		LEA DX,pcompletado
   		MOV AH,09H
    	INT 21H
           
   		MOV AL,paseh1 ;para mostrar el total de pases del equipo
   		AAM
   
   		MOV BX,AX
     
   		MOV DL,BH
   		ADD DL,30H ; PARA QUTARLE EL CODIGO ASCCI DEL NUMERO 
   		MOV AH,02H  ; SERVICIO PARA IMPRIMIR UN CARACTER TIENE Q ESTAR EN DL
  	    INT 21H
   
   		MOV DL,BL  ;HAY QUE PASARLO PORQUE SE DAÑA LO QUE HABIA EN AL CON LA INT 21H
   		ADD DL,30H ; PARA QUTARLE EL CODIGO ASCCI DEL NUMERO 
   		MOV AH,02H  ; SERVICIO PARA IMPRIMIR UN CARACTER TIENE Q ESTAR EN DL
   		INT 21H
       
   		gotoXY 16,13
   		LEA DX,golequipo1    ;PARA MOSTRAR LOS GOLES DEL EQUIPO
   		MOV AH,09H
    	INT 21H
    
    	MOV AL,golh1 ;para mostrar el total de goles del equipo2
   		AAM
   
   		MOV BX,AX
   		
     	MOV DL,BH
   		ADD DL,30H ; PARA QUTARLE EL CODIGO ASCCI DEL NUMERO 
   		MOV AH,02H  ; SERVICIO PARA IMPRIMIR UN CARACTER TIENE Q ESTAR EN DL
   		INT 21H
   
   		MOV DL,BL  ;HAY QUE PASARLO PORQUE SE DAÑA LO QUE HABIA EN AL CON LA INT 21H
   		ADD DL,30H ; PARA QUTARLE EL CODIGO ASCCI DEL NUMERO 
   		MOV AH,02H  ; SERVICIO PARA IMPRIMIR UN CARACTER TIENE Q ESTAR EN DL
   		INT 21H
    
    	gotoXY 38,11
		LEA DX,pcompletado    ;MUESTRA LOS PASES COMPLETADOS
   		MOV AH,09H
    	INT 21H
    
    	MOV AL,paseh2 ;para mostrar el total de pases del equipo
   		AAM
   
   		MOV BX,AX
     
   		MOV DL,BH
   		ADD DL,30H ; PARA QUTARLE EL CODIGO ASCCI DEL NUMERO 
   		MOV AH,02H  ; SERVICIO PARA IMPRIMIR UN CARACTER TIENE Q ESTAR EN DL
     	INT 21H
   
   		MOV DL,BL  ;HAY QUE PASARLO PORQUE SE DAÑA LO QUE HABIA EN AL CON LA INT 21H
   		ADD DL,30H ; PARA QUTARLE EL CODIGO ASCCI DEL NUMERO 
   		MOV AH,02H  ; SERVICIO PARA IMPRIMIR UN CARACTER TIENE Q ESTAR EN DL
   		INT 21H
       
    	gotoXY 38,13
		LEA DX,golequipo2    ;MUESTRA LOS GOLES DEL EQUIPO
   		MOV AH,09H
    	INT 21H
    
   		MOV AL,golh2 ;para mostrar el total de goles del equipo2
   		AAM
   
   		MOV BX,AX
     
   		MOV DL,BH
   		ADD DL,30H ; PARA QUTARLE EL CODIGO ASCCI DEL NUMERO 
   		MOV AH,02H  ; SERVICIO PARA IMPRIMIR UN CARACTER TIENE Q ESTAR EN DL
     	INT 21H
   
   		MOV DL,BL  ;HAY QUE PASARLO PORQUE SE DAÑA LO QUE HABIA EN AL CON LA INT 21H
   		ADD DL,30H ; PARA QUTARLE EL CODIGO ASCCI DEL NUMERO 
   		MOV AH,02H  ; SERVICIO PARA IMPRIMIR UN CARACTER TIENE Q ESTAR EN DL
   		INT 21H
  
   		MOV AH,01H    ;COMO UN SYSTEM PAUSE
   		INT 21H
     	jmp otra
    	
  		finfinp:
  		
  		ret  
  		
mediopartido endP
  
proc finpartido ;MUESTRA LOS RESULTADOS FINALES DEL PARTIDO
  	
  	cmp pases, 254
  		je auxfinpa
  		jmp finfinpa
  	
  	auxfinpa:
  		call clrscr
		drawRect 15,6,60,20
  		drawVLine 37,6,13,205,179,205
  		drawHLine 15,8,44,186,196,186  	
  	
    	gotoXY 1,1
 		LEA DX,PANT
   		MOV AH,09H
    	INT 21H
    
    	gotoXY 1,22
 		LEA DX,PANT 
   		MOV AH,09H
    	INT 21H
 
 		gotoXY 16,2
 		LEA DX,FINAL   
   		MOV AH,09H
    	INT 21H
  	
  		gotoXY 16,7
		LEA DX,ME1  
   		MOV AH,09H
    	INT 21H
    
    	gotoXY 38,7
		LEA DX,ME2    ;MUESTRA NOMBRE DEL EQUIPO
   		MOV AH,09H
    	INT 21H
        
    	gotoXY 16,11
		LEA DX,pcompletado    ;MUESTRA PASE COMPLETADO
   		MOV AH,09H
    	INT 21H
           
   		MOV AL,paseh1 ;MUESTRA EL TOTAL DE PASES
	    AAM
   
   		MOV BX,AX
     
   		MOV DL,BH
   		ADD DL,30H ; PARA QUTARLE EL CODIGO ASCCI DEL NUMERO 
   		MOV AH,02H  ; SERVICIO PARA IMPRIMIR UN CARACTER TIENE Q ESTAR EN DL
     	INT 21H
   
   		MOV DL,BL  ;HAY QUE PASARLO PORQUE SE DAÑA LO QUE HABIA EN AL CON LA INT 21H
   		ADD DL,30H ; PARA QUTARLE EL CODIGO ASCCI DEL NUMERO 
   		MOV AH,02H  ; SERVICIO PARA IMPRIMIR UN CARACTER TIENE Q ESTAR EN DL
   		INT 21H
       
   		gotoXY 16,13
   		LEA DX,golequipo1    ;MUESTRA LOS GOLES
   		MOV AH,09H
    	INT 21H
    
    	MOV AL,golh1 ;MUESTRA EL TOTAL DE GOLES
   		AAM
   
   		MOV BX,AX
     
   		MOV DL,BH
   		ADD DL,30H ; PARA QUTARLE EL CODIGO ASCCI DEL NUMERO 
   		MOV AH,02H  ; SERVICIO PARA IMPRIMIR UN CARACTER TIENE Q ESTAR EN DL
     	INT 21H
   
   		MOV DL,BL  ;HAY QUE PASARLO PORQUE SE DAÑA LO QUE HABIA EN AL CON LA INT 21H
   		ADD DL,30H ; PARA QUTARLE EL CODIGO ASCCI DEL NUMERO 
   		MOV AH,02H  ; SERVICIO PARA IMPRIMIR UN CARACTER TIENE Q ESTAR EN DL
   		INT 21H
    
    	gotoXY 38,11
		LEA DX,pcompletado    ;MUESTRA LOS PASES COMPLETADO
   		MOV AH,09H
    	INT 21H
    
    	MOV AL,paseh2 ;MUESTRA EL TOTAL DE PASES DEL EQUIPO
   		AAM
   
   		MOV BX,AX
     
   		MOV DL,BH
   		ADD DL,30H ; PARA QUTARLE EL CODIGO ASCCI DEL NUMERO 
   		MOV AH,02H  ; SERVICIO PARA IMPRIMIR UN CARACTER TIENE Q ESTAR EN DL
     	INT 21H
   
   		MOV DL,BL  ;HAY QUE PASARLO PORQUE SE DAÑA LO QUE HABIA EN AL CON LA INT 21H
   		ADD DL,30H ; PARA QUTARLE EL CODIGO ASCCI DEL NUMERO 
   		MOV AH,02H  ; SERVICIO PARA IMPRIMIR UN CARACTER TIENE Q ESTAR EN DL
   		INT 21H
       
    	gotoXY 38,13
		LEA DX,golequipo2    ;MUESTRA LOS GOLES
   		MOV AH,09H
    	INT 21H
    
   		MOV AL,golh2 ;MUESTRA LOS GOLES DEL EQUIPO2
   		AAM
   	
   		MOV BX,AX
     
   		MOV DL,BH
   		ADD DL,30H ; PARA QUTARLE EL CODIGO ASCCI DEL NUMERO 
   		MOV AH,02H  ; SERVICIO PARA IMPRIMIR UN CARACTER TIENE Q ESTAR EN DL
   		INT 21H
   
   		MOV DL,BL  ;HAY QUE PASARLO PORQUE SE DAÑA LO QUE HABIA EN AL CON LA INT 21H
   		ADD DL,30H ; PARA QUTARLE EL CODIGO ASCCI DEL NUMERO 
   		MOV AH,02H  ; SERVICIO PARA IMPRIMIR UN CARACTER TIENE Q ESTAR EN DL
   		INT 21H     ;TERMINA AQUI
   
   		mov al,golh1  ;COMPARA LOS GOLES DE LOS EQUIPOS
   		cmp al,golh2
   			jb gano2a
   		cmp al,golh2
   			je empatea
   
   		gotoXY 1,4
   		LEA DX,GANO1    ;SE MUESTRA CUANDO GANA EL EQUIPO1
   		MOV AH,09H
   		INT 21H
   		gotoXY 22,4
   		LEA DX,ME1    ;MUESTRA EL NOMBRE DEL EQUIPO1
   		MOV AH,09H
   		INT 21H
   		jmp jejeje
   
	gano2a:
   		gotoXY 41,4
   		LEA DX,GANO2    ;SE MUESTRA CUANDO GANA EL EQUIPO2
   		MOV AH,09H
   		INT 21H
   		gotoXY 59,4
   		LEA DX,ME2    ;MUESTRA EL NOMBRE DEL EQUIPO2
   		MOV AH,09H
   		INT 21H
  		jmp jejeje
  
	empatea:
  		gotoXY 33,4	;SE MUESTRA CUANDO HUBO UN EMPATE
  		LEA DX,EMPATE   
   		MOV AH,09H
   		INT 21H
  
	jejeje:
   		MOV AH,01H  ;GENERA UNA PAUSA QUE TERMINA AL LEER UN CARACTER   
   		INT 21H
    jmp iniciarotra
    	
  	finfinpa:
	
	
  	
  		ret  
  		
finpartido endP
  
  
Proc auxleer

	Mov SI,0  ;indice
    
    leyendo:
		Mov CL,[BX+SI] 
        cmp CL,'$'     ;si (cadena[si]== '$')
        JE Finleer         ;fin de la lectura
        getChar AL
        cmp AL,0Dh  ; ODh = enter
        JE Finleer
        cmp AL,08h  ; 08h = backSpace <--
        JE @@BackSpace
        Mov [BX+SI],AL  ;cadena[si] == AL
        inc si
        jmp leyendo
         
	@@BackSpace:
		dec SI
        Mov [BX+SI],20h ;20h =Espacio 
        call Retroceder
        jmp leyendo
        
	Finleer:
    
		ret
        
auxleer endP
    
Proc Retroceder

	push BX  ; salvo el valor de los registros DX y BX;
    push DX  ;
    Mov AH,03h    ;servicio que retorna a DH,DL la posicion
    int 10h       ;actual del cursor
    gotoXY DL,DH 
    Mov AL,20h    ;Escribe un blanco
    putChar AL    ;
    Mov AH,03h    ;servicio que retorna a DH,DL la posicion  
    int 10h       ;actual del cursor
    dec DL  
    gotoXY DL,DH  
  	pop DX
    pop BX  ;restauro el valor de DX y BX
    
    ret
    
endP Retroceder

  
  
Proc auxGetChar

	Mov AH,01h  ;servicio  para leer un caracter en pantalla
    int 21h 
    
    ret
    
auxGetChar endP

Proc auxEscribir
	push AX
    Mov AH,09h   ;servicio que escribe una cadena en la pantalla
    int 21h 
    pop AX
    
    ret    
    
endP auxEscribir 
   
   
proc pausa  ;genera una pausa aleatoria

	otravez:
		Mov ah,2Ch	
		Int 21h
		mov vrandom,dl 
		sub vrandom,1 
		cmp vrandom,2 
			jb otravez
			
	verificarn:
		cmp vrandom,dl
			je finpausa
			Mov ah,2Ch
			Int 21h
			jmp verificarn
	
	finpausa:
	
	ret
	
pausa endP

proc menuin
MENO:
     gotoxy 31,3        ;PARA UBICAR AL ENCABEZADO CASA DE APUESTAS
     WRITE LINEA3      ;ESCRIBIR CASA DE APUESTAS
   
     DRAWRECT 23,2,53,6 ;AQUI ESCRIBIR EL RECUADRO QUE ENCIARRA A BWIN
   
     GOTOXY 34,5        ;PARA UBICAR ENCABEZADO DE BWIN
     WRITE LINEA4      ;PARA COPIAR BWIN EN PANTALLA
     
     GOTOXY 24,9        ;PARA UBICAR ENCABEZADO MENU DE OPCIONES
     WRITE LINEA1      ;PARA COPIAR MENU DE OPCIONES EN LA PANTALLA
   
     GOTOXY 24,10       ;PARA COPIAR EL SUBRAYADO DE MENU DE OPCIONES
     WRITE LINEA2      ;ESCRIBIR EL SUBRAYADO

     GOTOXY 29,13       ;PARA UBICAR ENCABEZADO OPCION UNO (REALIZAR APUESTA)
     WRITE LINEA5      ;PARA COPIAR OPCION UNO EN PANTALLA
   
     GOTOXY 29,15       ;PARA UBICAR ENCABEZADO OPCION DOS CAMBIAR RANKING
     WRITE LINEA7      ;ESCRIBIR EL OPCION TRES EN PANTALLA
   
     
   
     GOTOXY 31,19       ;PARA UBICAR ELEGIR OPCION
     WRITE LINEA8      ;ESCRIBIR ELEGIR OPCION EN PANTALLA
   
    MOV AH,01H   ;PARA GUARDAR LA FORMACION DEL EQUIPO 1 EN ALINEACION 1
   	INT 21H		;SERVICIO PARA LEER UN CARACTER Y LO GUARDA EN AL
   	MOV CH,AL	;movemos a ch el caracter
   	SUB CH,30H	;lo convertimos en decimal
   	mov menu,ch  
     
        
   
     CMP MENU,1         ;COMPARAR MENU CON UNO (REALIZAR APUESTA) 
     JE despuesmenu         ;SI ES IGUAL A UNO ENTONCES SALTA A REALIZAR APUESTA 
     CMP MENU,2         ;COMPARAR MENU CON TRES (SALIR DEL SISTEMA)
     JE SALIR           ;SI ES IGUAL A TRES SE VA A SALIR
     JMP INCORRECTO     ;SI ES OTRA TECLA SE VA A INCORRECTO
   
    
    
    INCORRECTO:
     GOTOXY 25,21       ;PARA UBICAR ELEGIR OPCION
     WRITE INCORREC   ;ESCRIBIR ELEGIR OPCION EN PANTALLA
     GOTOXY 44,23       ;PARA UBICAR ELEGIR OPCION
     write CONTINUAR  ;ESCRIBIR ELEGIR OPCION EN PANTALLA
     
     MOV AH,01H        ;COMO UN SYSTEM PAUSE
     INT 21H
     
     CALL clrScr        ;LLAMO AL PROCEMIENTO LIMPIAR PANTALLA
     
     JMP MENO           ;Y CUANDO RETORNE SALTO MENO...(PEDIR OTRA VEZ OPCIONES)
	ret
   menuin endp



END
 
 















