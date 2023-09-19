# Lucas Chew
# 260971542

# The guess method is not work and is not completed.
# I couldn't figure out how to find the largest value, overwrite it if there was one larger

# Menu options
# r - read text buffer from file 
# p - print text buffer
# e - encrypt text buffer
# d - decrypt text buffer
# w - write text buffer to file
# g - guess the key
# q - quit

.data
MENU:              .asciiz "Commands (read, print, encrypt, decrypt, write, guess, quit):"
REQUEST_FILENAME:  .asciiz "Enter file name:"
REQUEST_KEY: 	 .asciiz "Enter key (upper case letters only):"
REQUEST_KEYLENGTH: .asciiz "Enter a number (the key length) for guessing:"
REQUEST_LETTER: 	 .asciiz "Enter guess of most common letter:"
ERROR:		 .asciiz "There was an error.\n"

FILE_NAME: 	.space 256	# maximum file name length, should not be exceeded
KEY_STRING: 	.space 256 	# maximum key length, should not be exceeded

.align 2		# ensure word alignment in memory for text buffer (not important)
TEXT_BUFFER:  	.space 10000
.align 2		# ensure word alignment in memory for other data (probably important)

letterArray:	.space 10000	# Create an array that will hold the frequency of letters

##############################################################
.text
		move $s1 $0 	# Keep track of the buffer length (starts at zero)
MainLoop:	li $v0 4		# print string
		la $a0 MENU
		syscall
		li $v0 12	# read char into $v0
		syscall
		move $s0 $v0	# store command in $s0			
		jal PrintNewLine

		beq $s0 'r' read
		beq $s0 'p' print
		beq $s0 'w' write
		beq $s0 'e' encrypt
		beq $s0 'd' decrypt
		beq $s0 'g' guess
		beq $s0 'q' exit
		b MainLoop

read:		jal GetFileName
		li $v0 13	# open file
		la $a0 FILE_NAME 
		li $a1 0		# flags (read)
		li $a2 0		# mode (set to zero)
		syscall
		move $s0 $v0
		bge $s0 0 read2	# negative means error
		li $v0 4		# print string
		la $a0 ERROR
		syscall
		b MainLoop
read2:		li $v0 14	# read file
		move $a0 $s0
		la $a1 TEXT_BUFFER
		li $a2 9999
		syscall
		move $s1 $v0	# save the input buffer length
		bge $s0 0 read3	# negative means error
		li $v0 4		# print string
		la $a0 ERROR
		syscall
		move $s1 $0	# set buffer length to zero
		la $t0 TEXT_BUFFER
		sb $0 ($t0) 	# null terminate the buffer 
		b MainLoop
read3:		la $t0 TEXT_BUFFER
		add $t0 $t0 $s1
		sb $0 ($t0) 	# null terminate the buffer that was read
		li $v0 16	# close file
		move $a0 $s0
		syscall
		la $a0 TEXT_BUFFER
		jal ToUpperCase
print:		la $a0 TEXT_BUFFER
		jal PrintBuffer
		b MainLoop	

write:		jal GetFileName
		li $v0 13	# open file
		la $a0 FILE_NAME 
		li $a1 1		# flags (write)
		li $a2 0		# mode (set to zero)
		syscall
		move $s0 $v0
		bge $s0 0 write2	# negative means error
		li $v0 4		# print string
		la $a0 ERROR
		syscall
		b MainLoop
write2:		li $v0 15	# write file
		move $a0 $s0
		la $a1 TEXT_BUFFER
		move $a2 $s1	# set number of bytes to write
		syscall
		bge $v0 0 write3	# negative means error
		li $v0 4		# print string
		la $a0 ERROR
		syscall
		b MainLoop
		write3:
		li $v0 16	# close file
		move $a0 $s0
		syscall
		b MainLoop

encrypt:		jal GetKey
		la $a0 TEXT_BUFFER
		la $a1 KEY_STRING
		jal EncryptBuffer
		la $a0 TEXT_BUFFER
		jal PrintBuffer
		b MainLoop

decrypt:		jal GetKey
		la $a0 TEXT_BUFFER
		la $a1 KEY_STRING
		jal DecryptBuffer
		la $a0 TEXT_BUFFER
		jal PrintBuffer
		b MainLoop

guess:		li $v0 4		# print string
		la $a0 REQUEST_KEYLENGTH
		syscall
		li $v0 5		# read an integer
		syscall
		move $s2 $v0
		
		li $v0 4		# print string
		la $a0 REQUEST_LETTER
		syscall
		li $v0 12	# read char into $v0
		syscall
		move $s3 $v0	# store command in $s0			
		jal PrintNewLine

		move $a0 $s2
		la $a1 TEXT_BUFFER
		la $a2 KEY_STRING
		move $a3 $s3
		jal GuessKey
		li $v0 4		# print String
		la $a0 KEY_STRING
		syscall
		jal PrintNewLine
		b MainLoop

exit:		li $v0 10 	# exit
		syscall

###########################################################
PrintBuffer:	li $v0 4          # print contents of a0
		syscall
		li $v0 11	# print newline character
		li $a0 '\n'
		syscall
		jr $ra

###########################################################
PrintNewLine:	li $v0 11	# print char
		li $a0 '\n'
		syscall
		jr $ra

###########################################################
PrintSpace:	li $v0 11	# print char
		li $a0 ' '
		syscall
		jr $ra

#######################################################
GetFileName:	addi $sp $sp -4
		sw $ra ($sp)
		li $v0 4		# print string
		la $a0 REQUEST_FILENAME
		syscall
		li $v0 8		# read string
		la $a0 FILE_NAME  # up to 256 characters into this memory
		li $a1 256
		syscall
		la $a0 FILE_NAME 
		jal TrimNewline
		lw $ra ($sp)
		addi $sp $sp 4
		jr $ra

###########################################################
GetKey:		addi $sp $sp -4
		sw $ra ($sp)
		li $v0 4		# print string
		la $a0 REQUEST_KEY
		syscall
		li $v0 8		# read string
		la $a0 KEY_STRING  # up to 256 characters into this memory
		li $a1 256
		syscall
		la $a0 KEY_STRING
		jal TrimNewline
		la $a0 KEY_STRING
		jal ToUpperCase
		lw $ra ($sp)
		addi $sp $sp 4
		jr $ra

###########################################################
# Given a null terminated text string pointer in $a0, if it contains a newline
# then the buffer will instead be terminated at the first newline
TrimNewline:	lb $t0 ($a0)
		beq $t0 '\n' TNLExit
		beq $t0 $0 TNLExit	# also exit if find null termination
		addi $a0 $a0 1
		b TrimNewline
TNLExit:		sb $0 ($a0)
		jr $ra

##################################################
# converts the provided null terminated buffer to upper case
# $a0 buffer pointer
ToUpperCase:	lb $t0 ($a0)
		beq $t0 $zero TUCExit
		blt $t0 'a' TUCSkip
		bgt $t0 'z' TUCSkip
		addi $t0 $t0 -32	# difference between 'A' and 'a' in ASCII
		sb $t0 ($a0)
TUCSkip:		addi $a0 $a0 1
		b ToUpperCase
TUCExit:		jr $ra

###################################################
# END OF PROVIDED CODE... 
# TODO: use this space below to implement required procedures
###################################################









##################################################
# null terminated buffer is in $a0
# null terminated key is in $a1
# t0 = Buffer (changeable)
# t1 = Original key
# t2 = Current Char
# t3 = 26
# t4 = Current Key 


EncryptBuffer:	la $t0, 0($a0)			#Load buffer into var
		la $t1, 0($a1) 			#Load key into address
		
		Encrypt1: 
		lb $t2, 0($t0)			#Take char and store in T2
		beqz $t2, EncryptEnd		#If end of string, return $ra
		
		lb $t4, 0($t1)			#Take first Key and store in T4
		beqz $t4, EncryptResetKey	#If end of string, reset address
		
		Encrypt2:
		blt $t2, 65, EncryptIterate	#If less than 65 ascii, next
		bge $t2, 91, EncryptIterate	#If greater equal 91 ascii, next
		
		li $t3, 26			#Load temp var with 26
		sub $t4, $t4, 65		#subtact 65 from key
		sub $t2, $t2, 65		#subtract 65 from ascii
		
		add $t2, $t2, $t4		#add key	
		div $t2, $t3			#divide value by 26
		
		mfhi $a0			#store mod value into original address
		addi $a0, $a0, 65		#add 65 back into value
		        
 		sb $a0, ($t0)			#save byte into address
 		        
		EncryptIterate:
		add $t0, $t0, 1			#Iterate string to the next char
		add $t1, $t1, 1			#Iterate key to the next char
		j Encrypt1
		
		EncryptEnd:
		jr $ra
		
		EncryptResetKey:
		la $t1, 0($a1) 			#Reload key into address
		lb $t4, 0($t1)
		j Encrypt2
		
		

##################################################
# null terminated buffer is in $a0
# null terminated key is in $a1

DecryptBuffer:	la $t0, 0($a0)			#Load buffer into var
		la $t1, 0($a1) 			#Load key into address
		
		Decrypt1: 
		lb $t2, 0($t0)			#Take char and store in T2
		beqz $t2, DecryptEnd		#If end of string, return $ra
		
		lb $t4, 0($t1)			#Take first Key and store in T4
		beqz $t4, DecryptResetKey	#If end of string, reset address
		
		Decrypt2:
		blt $t2, 65, DecryptIterate	#If less than 65 ascii, next
		bge $t2, 91, DecryptIterate	#If greater equal 91 ascii, next
		
		sub $t4, $t4, 65		#subtact 65 from key
		
		sub $t2, $t2, $t4		#subtract key	
		bge $t2, 65, Decrypt3		#if value is less than 65
		add $t2, $t2, 26		#add 26
		
		Decrypt3:
		sb $t2, ($t0)			#save byte into address
 		        
		DecryptIterate:
		add $t0, $t0, 1			#Iterate string to the next char
		add $t1, $t1, 1			#Iterate key to the next char
		j Decrypt1
		
		DecryptEnd:
		jr $ra
		
		DecryptResetKey:
		la $t1, 0($a1) 			#Reload key into address
		lb $t4, 0($t1)
		j Decrypt2

###########################################################
# a0 keySize - size of key length to guess
# a1 Buffer - pointer to null terminated buffer to work with
# a2 KeyString - on return will contain null terminated string with guess
# a3 common letter guess - for instance 'E' 

# Get frequency of letters in string (Array)
# Assume key len = 1
# Get key
# abs(key-mostFrequentLetter)+65
# return letter
GuessKey:
		#Initialize Vars
		la $t0, ($a0)			# Size of Key Length
		la $t1, ($a1)			# String
		la $t3, ($a3)			# Letter Guess
			
		#la letterArray, ($t5)		# Reset array to 0
		li $t9, 4			# Create constant
		
		CountLetters:			#Uses T2, T4, T8, T9
		lb $t2, 0($t1)			#Take char and store in T2
		beqz $t2, Guess1		#If end of string, begin guessing
		
		blt $t2, 65, CountLetters	#If less than 65 ascii, next
		bge $t2, 91, CountLetters	#If greater equal 91 ascii, next
		
		addi $t2, $t2, -65		#Remove 65 from ascii
		mult $t2, $t9			# Multiply number by 4
		mfhi $t8			# Store result in t8
		
		lb $t4, letterArray($t8)		#Store value from array into T4
		addi $t4, $t4, 1		#Add 1 to frequency
		sb $t4, letterArray($t8)		#Store value into array
		
		addi $t1, $t1, 1		#Iterate
		j CountLetters
		
		Guess1: # Iterate and get the largest number in the list. Set the value to 0. Repeat for the length of key.
		
		Guess2: # Store value into output
		addi $t5, $t5, 65
		sb $t5, ($a2)
		addi $a2, $a2, 1
		
		jr $ra
