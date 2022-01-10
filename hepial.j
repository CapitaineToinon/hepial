.class public Program
.super java/lang/Object
.method public static main([Ljava/lang/String;)V
.limit stack 20000
.limit locals 2
.var 0 is n I
ldc 0
istore 0
ldc 5
istore 0
getstatic java/lang/System/out Ljava/io/PrintStream;
iload 0
invokevirtual java/io/PrintStream/println(I)V
exit_label:
return
.end method
