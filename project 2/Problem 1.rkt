;; The first three lines of this file were inserted by DrRacket. They record metadata
;; about the language level of this file in a form that our tools can easily process.
#reader(lib "htdp-advanced-reader.ss" "lang")((modname |Problem 1|) (read-case-sensitive #t) (teachpacks ()) (htdp-settings #(#t constructor repeating-decimal #t #t none #f () #f)))
; Ngoc Pham
; Hoat Vu
; Sangwook Park
; CSC 135 - A2 - Problem 1
( define ( reverse-helper L )
   ( cond ( ( null? L ) '( ) )
          ( ( list? ( car L ) ) ( append ( reverse-helper ( cdr L ) )
                             ( list ( reverse-helper ( car L ) ) ) ) ) 
          ( else ( append ( reverse-helper ( cdr L ) ) ( list ( car L ) ) ) ) ) ) 

( define ( reverse-name-order L )
   ( if ( null? L ) '( )
   ( append ( reverse-name-order ( cdr L ) ) ( list ( car L ) ) ) ) )

(display(reverse-name-order (reverse-helper '((Hoat Vu) (Ngoc Pham) (Sangwook Park))))) ;test case
