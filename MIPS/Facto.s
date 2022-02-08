.data   #stored global variables
    inputMsg:   .asciiz "input number: "
    userInput:  .word   0   #default at 0
    outputMsg:  .ascii "factorial is: "
    result:     .word   0   #default at 0

.text
    .globl main
    main: #main function, first to run & take user input
        li      $v0, 4
        la      $a0, inputMsg
        syscall

        li      $v0, 5
        syscall

        sw      $v0, userInput

        #after taking the input, call the Factorial function
        lw      $a0, userInput
        jal     facto   #jump to factorial function
        sw      $v0, result #result from factorial will be stored in $v0

        li      $v0, 4
        la      $a0, outputMsg
        syscall

        #prints the result to the screen
        li      $v0, 1
        lw      $a0, result
        syscall

        li      $v0, 10
        syscall #end


.globl facto
    facto:
        subu    $sp, $sp, 8
        sw      $ra, ($sp)
        sw      $s0, 4($sp)

        li      $v0, 1              # edge case (if userInput is 0) return 1
        beq		$a0, 0, finished	# if $a0 == 0 then goto finishedtarget

        #else (if its not 0)
        move 	$s0, $a0		# $s0 = $a0
        sub		$a0, $a0, 1		# $a0 = $a0 - 1 (subtract 1 in each itteration)
        jal     facto           #recurssion

        mul     $v0, $s0, $v0 
        
        finished:
            lw      $ra, ($sp)
            lw      $s0, 4($sp)
            addu    $sp, $sp, 8         # $sp = $sp + 8
            jr      $ra
            
        