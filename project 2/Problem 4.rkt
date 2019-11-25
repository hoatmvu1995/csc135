; Ngoc Pham
; Hoat Vu
; Sangwook Park
; CSC 135 - A2 - Problem 4
#lang racket
(define (lastt lst) ;get last element of the list
  (if (= (length lst) 1)
      (car lst)
      (lastt (cdr lst))))

(define (choose a b) ; between two list with depth, choose the list with bigger depth
  (cond ((< (lastt a) (lastt b)) (car b))
        ((> (lastt a) (lastt b)) (car a))
        (else (car a))))

(define (find-depest L)
  (cond ((null? L) (list L -1)) ;if list is null return -1, base case
        ((not(list? L)) (list L -1)) ;if list is just an atom also return -1, also a base case
        ((not(list? (lastt L))) (list L 0)) ;if list has no nested list, return 0 for that depth
        ;otherwise return the greater of recursive call (depth(car list)) +1 or (depth(cdr list))
        (else (list (choose (find-depest(car L)) (find-depest(cdr L)))
                    (max (+ 1 (lastt (find-depest(car L)))) (lastt (find-depest(cdr L))))))))
     

;test cases 
(display(find-depest '( (a b)(b d e (f  g))(r(s (u v (m n))))(q (p)) )))