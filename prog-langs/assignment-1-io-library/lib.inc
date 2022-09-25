global exit
global string_length
global print_string
global print_char
global print_newline
global print_uint
global print_int
global string_equals
global read_char
global read_word
global parse_uint
global parse_int
global string_copy
section .data

section .text
 
; Принимает код возврата и завершает текущий процесс
exit: 
    mov rax, 60 ; exit syscall
    syscall

; Принимает указатель на нуль-терминированную строку, возвращает её длину
string_length:
    xor rcx, rcx ; обнуляем счётчик

    .loop:
        mov al, [rdi+rcx] ; загрузка следующего символа

        test al, al
        je .end ; если это нуль-терминатор, то строка окончена

        inc rcx ; прибавление к количеству

        jmp .loop

    .end:
        mov rax, rcx ; выгрузка результата
        ret

; Принимает указатель на нуль-терминированную строку, выводит её в stdout
print_string:
    call string_length
    mov rdx, rax ; записываем количество байт для вывода (количество символов)
    
    mov rsi, rdi ; передаем указатель на строку в параметр syscall
    mov rax, 1 ; syscall for write
    mov rdi, 1 ; дескриптор (stdout)

    syscall

    ret

; Принимает код символа и выводит его в stdout
print_char:
    push rdi ; сохраняем код символа для указания на него

    mov rax, 1 ; номер syscall (write)
    mov rdi, 1 ; stdout
    mov rsi, rsp ; берем указатель на код символа для печати
    mov rdx, 1 ; количество байт (1 символ)

    syscall

    pop rdi ; очистка стека
    
    ret

; Переводит строку (выводит символ с кодом 0xA)
print_newline:
    mov rdi, 0xA ; загрузка символа \n
    call print_char
    ret


; Выводит беззнаковое 8-байтовое число в десятичном формате 
; Совет: выделите место в стеке и храните там результаты деления
; Не забудьте перевести цифры в их ASCII коды.
print_uint:
    mov r10, rsp ; сохранение указателя стека
    mov rax, rdi ; перемещаем делимое в рабочий регистр
    mov r11, 10 ; система счисления - 10

    dec rsp ; сдвигаем stack pointer
    mov byte[rsp], 0x0 ; добавляем в конец нуль-терминатор

    .loop:
        xor rdx, rdx ; очистка регистра
        div r11 ; производим деление на 10

        add rdx, 0x30 ; переводим в ASCII-код

        dec rsp ; сдвигаем stack pointer
        mov [rsp], dl ; сохраняем цифру

        ; проверка на конец деления
        test rax, rax
        jz .printing

        jmp .loop
    .printing:
        mov rdi, rsp
        call print_string

        mov rsp, r10 ; восстановление указателя стека
        ret

; Выводит знаковое 8-байтовое число в десятичном формате 
print_int:
    ; проверка на отрицательность
    cmp rdi, 0
    jge .print_number; если больше либо равно 0

    neg rdi
    push rdi
    ; печать символа минуса
    mov rdi, 0x2D
    call print_char

    pop rdi
    .print_number:
        call print_uint
        ret

; Принимает два указателя на нуль-терминированные строки, возвращает 1 если они равны, 0 иначе
string_equals:
    cmp rdi, rsi ; сравнение двух указателей
    je .equals ; если они равны, то строки равны

    call string_length ; подсчёт символов 1 строки
    mov r10, rdi ; сохранение указателя на первую строку
    mov r11, rax ; сохранение количества символов для первой строки

    mov rdi, rsi ; передача в виде параметра указателя на 2 строку
    call string_length ; подсчёт символов 2 строки

    cmp rax, r11 ; сравнение размеров
    jne .not_equals ; если размеры различны, то строчки не равны

    mov rsi, rdi ; восстановление 2ого указателя
    mov rdi, r10 ; восстановление 1ого указателя

    .loop:
        ; загрузка символов из строчек
        mov r10b, [rdi]
        mov r11b, [rsi]

        cmp r10b, r11b ; сравнение двух символов

        jne .not_equals ; если символы не совпадают

        ; проверка на конец строки
        test r10b, r11b
        jz .equals

        ; сдвиг на следующие символы
        inc rdi
        inc rsi
        jmp .loop

    ; строки равны
    .equals:
        mov rax, 1
        ret
    ; строки не равны
    .not_equals:
        xor rax, rax
        ret

; Читает один символ из stdin и возвращает его. Возвращает 0 если достигнут конец потока
read_char:
    xor rax, rax ; read syscall
    xor rdi, rdi ; stdin
    mov rdx, 1
    push rax ; подготовка к записи в стэк
    mov rsi, rsp ; указание на запись в стэк

    syscall

    pop rax ; извлечение значения из стэка

    ret


; Принимает: адрес начала буфера, размер буфера
; Читает в буфер слово из stdin, пропуская пробельные символы в начале, .
; Пробельные символы это пробел 0x20, табуляция 0x9 и перевод строки 0xA.
; Останавливается и возвращает 0 если слово слишком большое для буфера
; При успехе возвращает адрес буфера в rax, длину слова в rdx.
; При неудаче возвращает 0 в rax
; Эта функция должна дописывать к слову нуль-терминатор

read_word:
    ;dec rsi ; уменьшение для учета нуль-терминатора

    mov r10, rdi ; сохранение указателя

    .skip_space_symbols:
        push r10
        push rsi
        call read_char
        pop rsi
        pop r10

        cmp al, 0x20
        je .skip_space_symbols

        cmp al, 0x9
        je .skip_space_symbols

        cmp al, 0xA
        je .skip_space_symbols
    xor rcx, rcx
    .loop:
        cmp al, 0x0
        je .end_of_stream

        cmp al, 0x20
        je .end_of_stream

        cmp al, 0x9
        je .end_of_stream

        cmp al, 0xA
        je .end_of_stream

        cmp rcx, rsi
        jg .fault

        mov byte[r10+rcx], al
        inc rcx

        push r10
        push rsi
        push rcx
        call read_char
        pop rcx
        pop rsi
        pop r10

        jmp .loop

    .fault:
        xor rax, rax
        ret

    .end_of_stream:
        mov byte[r10+rcx], 0x0
        mov rax, r10
        mov rdx, rcx
        ret

; Принимает указатель на строку, пытается
; прочитать из её начала беззнаковое число.
; Возвращает в rax: число, rdx : его длину в символах
; rdx = 0 если число прочитать не удалось
; Принимает указатель на строку, пытается
; прочитать из её начала беззнаковое число.
; Возвращает в rax: число, rdx : его длину в символах
; rdx = 0 если число прочитать не удалось
parse_uint:
    xor rax, rax
    xor rdx, rdx
    xor r10, r10
    mov r11, 10
    .loop:
        mov r10b, byte[rdi+rdx] ; чтение символа

        ; проверка на вхождение символа в диапазон цифр
        cmp r10b, 0x30
        jl .result

        cmp r10b, 0x39
        jg .result

        ; сохранение rdx
        push rdx

        ; сдвиг символа
        mul r11

        ; выгрузка rdx
        pop rdx

        ; перевод из ASCII кода в цифру
        sub r10b, 0x30

        ; прибавление цифры к числу
        add rax, r10

        ; сдвиг указателя
        inc rdx
        jmp .loop
    .result:
        ret




; Принимает указатель на строку, пытается
; прочитать из её начала знаковое число.
; Если есть знак, пробелы между ним и числом не разрешены.
; Возвращает в rax: число, rdx : его длину в символах (включая знак, если он был) 
; rdx = 0 если число прочитать не удалось
parse_int:
    mov r10b, [rdi]

    cmp r10b, 0x2D ; проверка на минус
    je .print_neg_number

    cmp r10b, 0x2B ; проверка на плюс
    je .print_plus_number

    ; проверка на вхождение символа в диапазон цифр
    cmp r10b, 0x30
    jl .fault

    cmp r10b, 0x39
    jg .fault

    jmp .print_number

    .print_plus_number:
        inc rdi ; удаление плюса
        call parse_uint
        inc rdx ; учет занка
        ret
    .print_number:
        call parse_uint
        ret
    .print_neg_number:
        inc rdi ; удаление минуса
        call parse_uint
        inc rdx ; учет знака
        neg rax
        ret
    .fault:
        xor rdx, rdx
        ret

; Принимает указатель на строку, указатель на буфер и длину буфера
; Копирует строку в буфер
; Возвращает длину строки если она умещается в буфер, иначе 0
string_copy:

    ; подсчёт символов в исходной строке
    push rsi
    push rdx
    call string_length
    pop rdx
    pop rsi

    inc rax ; увеличение размера под 0x0

    cmp rax, rdx
    jg .fault ; если не влезает в буфер - fail

    xor rcx, rcx

    .loop:
        ; копирование символа
        mov r10, [rdi+rcx]
        mov [rsi+rcx], r10

        ; проверка на конец буфера
        cmp rax, rcx
        je .end

        inc rcx
        
        jmp .loop

    .end:
        ; добавление нуль-терминатора
        inc rcx
        xor r10, r10
        mov [rsi+rcx], r10 
        ret
    
    .fault:
        xor rax, rax
        ret
