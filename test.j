.class public Hepial
.super java/lang/Object
.method public static main([Ljava/lang/String;)V

        .limit stack 1000
        .limit locals 1000
        .var 0 is n I
        .var 1 is b Z
        ldc 0
        istore 0 ; n
        ldc 1
        istore 1 ; encore

LOOP_424e37ae-5bbd-4d4f-ba69-5749729d8821:
        dup
        iload 1 ; encore
        if_icmpne ENDLOOP_424e37ae-5bbd-4d4f-ba69-5749729d8821
        getstatic java/lang/System/out Ljava/io/PrintStream;
        iload 0 ; n
        invokevirtual java/io/PrintStream/println(I)V
        iload 0 ; n
        ldc 1
        iadd
        istore 0 ; n
        goto LOOP_424e37ae-5bbd-4d4f-ba69-5749729d8821
ENDLOOP_424e37ae-5bbd-4d4f-ba69-5749729d8821:
        return ; return from main
.end method