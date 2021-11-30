.data   #stored global variables
    inputMsg:   .asciiz "input your numbers: "
    numInt:     .word   6   #number of integers
    userInput:  .word   0 1 3 2 4 1   #array of integers
    result:     .word   0   #default at 0

.text
    .globl main
    main:
        la      $t1, userInput  #take in elements of the user input
        lw      $s0, numInt     #allocate space for length of array
        subu    $s0, $s0, 1   

        addu    $s5, $zero, $zero
        addu    $s1, $zero, $zero

    for:                        #outer for loop to sort the numbers
        addu    $s2, $zero, $zero
        subu    $t9, $s0, $s1

        innerLoop:              #for comparing userInput[i+1]
            addu    $t2, $t1, 4
            lw      $t4, ($t1)      #index of the array userInput[i]
            lw      $t5, ($t2)

            bleu    $t4, $t5, ignore    #if(userInput[i] <= userInput[i+1])
            sw      $t4, ($t2)
            sw      $t5, ($t1)
            addu    $s5, $zero, 1

            ignore:
                beq     $s2, $t9, endInnerLoop
                addu    $s2, $s2, 1     #increment inner variable i++
                addu    $t1, $t1, 4
                addu    $t2, $t2, 4 
                b       innerLoop
        endInnerLoop:
            beqz    $s5, endFor
            beq		$s1, $s0, endFor	# if $s1 == $s0 then goto endFor
            addu    $s1, $s1, 1
            la      $t1, userInput
            b		for			# branch to for
            
    endFor:
        la      $t1, userInput        
        and		$s1, $zero, $zero	

    displayResult:
        lw      $a0, ($t1)
        addu    $v0, $zero, 1
        syscall

        beq     $s1, $s0, closeDisplay
        addu    $s1, $s1, 1
        addu    $t1, $t1, 4
        b		displayResult			# branch to displayResult
        
    closeDisplay:

        