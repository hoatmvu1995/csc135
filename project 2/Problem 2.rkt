;; The first three lines of this file were inserted by DrRacket. They record metadata
;; about the language level of this file in a form that our tools can easily process.
#reader(lib "htdp-advanced-reader.ss" "lang")((modname |Problem 2|) (read-case-sensitive #t) (teachpacks ()) (htdp-settings #(#t constructor repeating-decimal #t #t none #f () #f)))
; Ngoc Pham
; Hoat Vu
; Sangwook Park
; CSC 135 - A2 - Problem 2
(define (is-prime-number P)
  (letrec(
          (notDividable
           (lambda(P Q)
             (cond ((= Q 1) #t)                        ; Not devidable if remainder of dividing the number P by at least one number Q
                   ((zero? (modulo P Q)) #f)           ; There is exist a Q such that Q < P, Q modulo P is zero => not dividable
                   (else (notDividable P(- Q 1)))      
               )
             )
           )
          )
    (notDividable P(quotient P  2))
    )
  )
                                    
;test cases        
(is-prime-number 27)
(is-prime-number 19)
(is-prime-number 2)
(is-prime-number 23)
(is-prime-number 49)