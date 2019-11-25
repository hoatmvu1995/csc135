; Ngoc Pham
; Hoat Vu
; Sangwook Park
; CSC 135 - A2 - Problem 3
#lang racket
(define-namespace-anchor a)
(define ns (namespace-anchor->namespace a))

 (define (play-game f g)
   (define tem_f (eval f ns))
   (define tem_g (eval g ns))
  (lambda (X Y)
         (cond ((> (length X) (length Y))(tem_f(tem_g Y)))
               (else (tem_g(tem_f X))))))

;test cases
(display((play-game 'cdr 'last) '((5 6) (2 3) (1 1))'((1 2) (3 4))  )) 
(display "\n")
(display((play-game 'car 'last) '((1 2) (3 4)) '((5 6) (2 3) (1 1))  ))