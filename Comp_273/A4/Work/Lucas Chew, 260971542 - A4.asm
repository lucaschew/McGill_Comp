# Lucas Chew 260971542

# MADD1 and MADD2 doesn't seem to work and I couldn't get it working.
# It works when size is 1, but fails if it is greater and the sum is a negative number
# NaN is produced as it is unable to take the sqrt of a negative number



.data
TestNumber:	.word 2		# TODO: Which test to run!
				# 0 compare matrices stored in files Afname and Bfname
				# 1 test Proc using files A through D named below
				# 2 compare MADD1 and MADD2 with random matrices of size Size
				
Proc:		MADD1		# Procedure used by test 2, set to MADD1 or MADD2		
				
Size:		.word 64		# matrix size (MUST match size of matrix loaded for test 0 and 1)

Afname: 	.asciiz "A64.bin"
Bfname: 	.asciiz "B64.bin"
Cfname:		.asciiz "C64.bin"
Dfname:	 	.asciiz "D64.bin"

#################################################################
# Main function for testing assignment objectives.
# Modify this function as needed to complete your assignment.
# Note that the TA will ultimately use a different testing program.
.text
main:		la $t0 TestNumber
		lw $t0 ($t0)
		beq $t0 0 compareMatrix
		beq $t0 1 testFromFile
		beq $t0 2 compareMADD
		li $v0 10 # exit if the test number is out of range
        		syscall	

compareMatrix:	la $s7 Size	
		lw $s7 ($s7)		# Let $s7 be the matrix size n

		move $a0 $s7
		jal mallocMatrix		# allocate heap memory and load matrix A
		move $s0 $v0		# $s0 is a pointer to matrix A
		la $a0 Afname
		move $a1 $s7
		move $a2 $s7
		move $a3 $s0
		jal loadMatrix
	
		move $a0 $s7
		jal mallocMatrix		# allocate heap memory and load matrix B
		move $s1 $v0		# $s1 is a pointer to matrix B
		la $a0 Bfname
		move $a1 $s7
		move $a2 $s7
		move $a3 $s1
		jal loadMatrix
	
		move $a0 $s0
		move $a1 $s1
		move $a2 $s7
		jal check
		
		li $v0 10      	# load exit call code 10 into $v0
        	syscall         # call operating system to exit	

testFromFile:	la $s7 Size	
		lw $s7 ($s7)		# Let $s7 be the matrix size n

		move $a0 $s7
		jal mallocMatrix		# allocate heap memory and load matrix A
		move $s0 $v0		# $s0 is a pointer to matrix A
		la $a0 Afname
		move $a1 $s7
		move $a2 $s7
		move $a3 $s0
		jal loadMatrix
	
		move $a0 $s7
		jal mallocMatrix		# allocate heap memory and load matrix B
		move $s1 $v0		# $s1 is a pointer to matrix B
		la $a0 Bfname
		move $a1 $s7
		move $a2 $s7
		move $a3 $s1
		jal loadMatrix
	
		move $a0 $s7
		jal mallocMatrix		# allocate heap memory and load matrix C
		move $s2 $v0		# $s2 is a pointer to matrix C
		la $a0 Cfname
		move $a1 $s7
		move $a2 $s7
		move $a3 $s2
		jal loadMatrix
	
		move $a0 $s7
		jal mallocMatrix		# allocate heap memory and load matrix A
		move $s3 $v0		# $s3 is a pointer to matrix D
		la $a0 Dfname
		move $a1 $s7
		move $a2 $s7
		move $a3 $s3
		jal loadMatrix		# D is the answer, i.e., D = AB+C 
	
		# TODO: add your testing code here
		move $a0, $s0	# A
		move $a1, $s1	# B
		move $a2, $s2	# C
		move $a3, $s7	# n
		
		la $ra ReturnHere
		la $t0 Proc	# function pointer
		lw $t0 ($t0)	
		jr $t0		# like a jal to MADD1 or MADD2 depending on Proc definition

ReturnHere:	move $a0 $s2	# C
		move $a1 $s3	# D
		move $a2 $s7	# n
		jal check	# check the answer

		li $v0, 10      	# load exit call code 10 into $v0
	        syscall         	# call operating system to exit	

compareMADD:	la $s7 Size
		lw $s7 ($s7)	# n is loaded from Size
		mul $s4 $s7 $s7	# n^2
		sll $s5 $s4 2	# n^2 * 4

		move $a0 $s5
		li   $v0 9	# malloc A
		syscall	
		move $s0 $v0
		move $a0 $s5	# malloc B
		li   $v0 9
		syscall
		move $s1 $v0
		move $a0 $s5	# malloc C1
		li   $v0 9
		syscall
		move $s2 $v0
		move $a0 $s5	# malloc C2
		li   $v0 9
		syscall
		move $s3 $v0	
	
		move $a0 $s0	# A
		move $a1 $s4	# n^2
		jal  fillRandom	# fill A with random floats
		move $a0 $s1	# B
		move $a1 $s4	# n^2
		jal  fillRandom	# fill A with random floats
		move $a0 $s2	# C1
		move $a1 $s4	# n^2
		jal  fillZero	# fill A with random floats
		move $a0 $s3	# C2
		move $a1 $s4	# n^2
		jal  fillZero	# fill A with random floats

		move $a0 $s0	# A
		move $a1 $s1	# B
		move $a2 $s2	# C1	# note that we assume C1 to contain zeros !
		move $a3 $s7	# n
		jal MADD1

		move $a0 $s0	# A
		move $a1 $s1	# B
		move $a2 $s3	# C2	# note that we assume C2 to contain zeros !
		move $a3 $s7	# n
		jal MADD2

		move $a0 $s2	# C1
		move $a1 $s3	# C2
		move $a2 $s7	# n
		jal check	# check that they match
	
		li $v0 10      	# load exit call code 10 into $v0
        		syscall         	# call operating system to exit	

###############################################################
# mallocMatrix( int N )
# Allocates memory for an N by N matrix of floats
# The pointer to the memory is returned in $v0	
mallocMatrix: 	mul  $a0, $a0, $a0	# Let $s5 be n squared
		sll  $a0, $a0, 2		# Let $s4 be 4 n^2 bytes
		li   $v0, 9		
		syscall			# malloc A
		jr $ra
	
###############################################################
# loadMatrix( char* filename, int width, int height, float* buffer )
.data
errorMessage: .asciiz "FILE NOT FOUND" 
.text
loadMatrix:	mul $t0 $a1 $a2 	# words to read (width x height) in a2
		sll $t0 $t0  2	  	# multiply by 4 to get bytes to read
		li $a1  0     		# flags (0: read, 1: write)
		li $a2  0     		# mode (unused)
		li $v0  13    		# open file, $a0 is null-terminated string of file name
		syscall
		slti $t1 $v0 0
		beq $t1 $0 fileFound
		la $a0 errorMessage
		li $v0 4
		syscall		  	# print error message
		li $v0 10         	# and then exit
		syscall		
fileFound:	move $a0 $v0     	# file descriptor (negative if error) as argument for read
  		move $a1 $a3     	# address of buffer in which to write
		move $a2 $t0	  	# number of bytes to read
		li  $v0 14       	# system call for read from file
		syscall           	# read from file
		# $v0 contains number of characters read (0 if end-of-file, negative if error).
                	# We'll assume that we do not need to be checking for errors!
		# Note, the bitmap display doesn't update properly on load, 
		# so let's go touch each memory address to refresh it!
		move $t0 $a3	# start address
		add $t1 $a3 $a2  	# end address
loadloop:	lw $t2 ($t0)
		sw $t2 ($t0)
		addi $t0 $t0 4
		bne $t0 $t1 loadloop		
		li $v0 16	# close file ($a0 should still be the file descriptor)
		syscall
		jr $ra	

##########################################################
# Fills the matrix $a0, which has $a1 entries, with random numbers
fillRandom:	li $v0 43
		syscall		# random float, and assume $a0 unmodified!!
		swc1 $f0 0($a0)
		addi $a0 $a0 4
		addi $a1 $a1 -1
		bne  $a1 $zero fillRandom
		jr $ra

##########################################################
# Fills the matrix $a0 , which has $a1 entries, with zero
fillZero:	sw $zero 0($a0)	# $zero is zero single precision float
		addi $a0 $a0 4
		addi $a1 $a1 -1
		bne  $a1 $zero fillZero
		jr $ra



######################################################
# TODO: void subtract( float* A, float* B, float* C, int N )  C = A - B
subtract: 	
		la $t1, 0($a3)			# Load N in $t1
		mul $t1 $t1, $t1		# Store N^2 in $t1
		
		beqz $t1, subtractEnd		# if N^2 == 0, return C
		
		la $t5, ($a0)		# Load Vars
		la $t6, ($a1)
		la $t7, ($a2)
		
		subtractLoop:
		l.s $f0, ($t5)		# Load Ai
		l.s $f1, ($t6) 		# Load Bi
		
		sub.s $f2, $f0, $f1	# Ci = Ai - Bi
		
		s.s $f2, ($t7)		# Store Ci 
		
		addi $t5, $t5, 4		# Add 4 bytes to addresses
		addi $t6, $t6, 4			
		addi $t7, $t7, 4
		
		sub $t1, $t1, 1			# Subtract 1 from counter
		bgtz $t1, subtractLoop		# If counter > 0, goto subtractLoop
		
		subtractEnd:
		jr $ra

#################################################
# TODO: float frobeneousNorm( float* C, int N )
# Let $a0 = Afname, Size = N
# $f0 is the return

frobeneousNorm: 	
		la $t1, 0($a1)		# Load N into temp var
		mul $t1, $t1, $t1	# N^2
		 
		beqz $t1, frobenousNormEnd	#If N == 0, end
		
		la $t5, ($a0)			# Load Float A
		mtc1 $zero, $f0			# Put 0 into $f0
		
		frobenousNormLoop:
		l.s $f1, ($t5)			# Load float into $f1
		
		add.s $f0, $f0, $f1		# Add value into sum
		
		#mov.s $f12, $f0
		#li $v0, 2
		#syscall
		
		addi $t5, $t5, 4			# Iterate to next float
		sub $t1, $t1, 1			# Counter--
		bgtz $t1, frobenousNormLoop	# Restart loop if N > 0
		
		frobenousNormEnd:
		sqrt.s $f0, $f0
		jr $ra

#################################################
# TODO: void check ( float* A, float* B, int N )
# Print the forbeneous norm of the difference of A and B
# a0 -> A, a1 -> B, a2 -> size
check: 		
		la $t0, ($ra)	# Load return address into temp var
				# Some reason, it loops	
		
		la $a3, ($a2)		# Move Size to $a3
		la $a2, ($a0)		# Set $a2 to $a0				
		jal subtract		# Call subtract (A, B, A, Size)
		
		la $a1, ($s7)		# Set $a1 to size
		jal frobeneousNorm	# Call frobeneousNorm (A, Size) 
		
		mov.s $f12, $f0		# Move return float to $f12
		li $v0, 2		# Call to set print to float
		syscall			# Print
		
		la $ra, ($t0)		# Set return address back to original
        	jr $ra			# Return

##############################################################
# TODO: void MADD1( float*A, float* B, float* C, int N )
MADD1: 				
							
		# Load C matricies
		la $t7, ($a2)
		
		la $t1, ($zero)			# Create i counter = 0
		beqz $a3, MADD1_End		# If size == 0, goto MADD1_End
		
		MADD1_ILoop:
		
		la $t2, ($zero)				# Create j counter = 0
		la $t6, ($a1)				# Load B Matrix
		
			MADD1_JLoop:
			
			la $t5, ($a0)			# Load A matrix
			la $t3, ($zero)			# Create k counter = 0
			
				MADD1_KLoop:
				
				l.s $f0, ($t5)			# Load float from A
				l.s $f1, ($t6)			# Load float from B
				l.s $f2, ($t7)			# Load float from C
				
				#li $v0, 2
				#mov.s $f12, $f0
				#mov.s $f12, $f1
				#mov.s $f12, $f2
				#syscall
				
				mul.s $f7, $f0, $f1		# Store A*B
				add.s $f2, $f2, $f7		# Float C = C + A*B
				s.s $f2, ($t7)
				
				#mov.s $f12, $f2
				#syscall
				
				addi $t5, $t5, 4		# Iterate A  
				
				addi $t3, $t3, 1		# Add 1 to k counter
				blt $t3, $s7, MADD1_KLoop	# If k counter < size, goto MADD1_KLoop
			
			addi $t6, $t6, 4		# Add 4 to B
			mul $v0, $a3, 4			# 4 * size
			sub $t6, $t6, $v0		# B = B - 4*size
			
			sub $t5, $t6, $v0		# A = A -4*size
			
			addi $t7, $t7, 4		# Iterate C
			addi $t2, $t2, 1		# Add 1 to j counter
			blt $t2, $s7, MADD1_JLoop	# If j counter < size, goto MADD1_JLoop
		
		addi $t1, $t1, 1		# Subtract one from i counter
		blt  $t1, $s7, MADD1_ILoop	# If i counter < size, goto MADD1_ILoop
		
		MADD1_End:
		jr $ra

#########################################################
# TODO: void MADD2( float*A, float* B, float* C, N )
# Let bsize = 4
# a loop = $t0
# b loop = $t1
# c loop = $t2
# aa loop = $t3
# bb loop = $t4
# A = $t5, B = $t6, C = $t7
# Size N = $a3
# Sum = $f10
MADD2: 		

beqz $a3, MADD2_End	# If size == 0, goto End Return 

li $t0, 0			# Create a counter
	MADD2_ALoop:
	li $t1, 0			# Create b counter
	
		MADD2_BLoop:
		li $t2, 0		# Create c counter
		la $t5, ($a0)		# Load A Matrix
		la $t7, ($a2)		# Load C Matrix
		
			MADD2_CLoop:
			add $t3, $zero, $t0		# Create aa counter = a
			
			la $t6, ($a1)			# Load B Matrix
	
				MADD2_AALoop:
				#l.s $f10, ($zero)			# Sum = 0
				mtc1 $zero, $f10
				
				add $t4, $zero, $t1 		# Create bb counter = b
				
					MADD2_BBLoop:
					
					l.s $f1, ($t5)			# Float for Matrix A
					l.s $f2, ($t6)			# Float for Matrix B
					
					mul.s $f3, $f1, $f2		# Calculate A*B
					add.s $f10, $f10, $f3		# Sum += A*B
					
					addi $t5, $t5, 4		# Increment A Matrix to A[][kk+1]
					
					mul $v0, $a3, 4			# $v0 is 4 * size
					add $t6, $t6, $v0		# Increment B Matrix to B[k+1][]
					
					addi $t4, $t4, 1		# Increment bb counter by 1
					j MADD2_BBMin			# Calculate min (b + size, size) and store in $v0
					
					MADD2_BBMinReturn:
					blt $t4, $v0, MADD2_BBLoop	# If bb counter < $v0, repeat
				
				
				
				l.s $f0, ($t7)			# Load float at Matrix C
				add.s $f0, $f0, $f10		# float C += sum
				s.s $f0, ($t7)			# Save float C back into Matrix
				
				mul $v0, $a3, 4			# Go from A[i+1][kk] to A[i][kk]
				sub $t5, $t5, $v0
				add $t5, $t5, 4			# Go to A[i][kk+1]
				
				mul $v0, $a3, $a3		# Calculate size * size
				mul $v0, $v0, 4			# Get size^2 * 4
				sub $t6, $t6, $v0		# Set B Matrix to B[k+1][j]
				addi $t6, $t6, 4		# Set B Matrix to B[][j+1]
				
				addi $t7, $t7, 4		# Increment C Matrix C[][aa+1]
				
				addi $t3, $t3, 1		# Increment aa counter by 1
				j MADD2_AAMin			# Goto function call
				
				MADD2_AAMinReturn:		# $v0 holds min(aa+bize, n)
				blt $t3, $v0, MADD2_AALoop	# If aa counter < $v0, repeat
			
			mul $v0, $a3, 4			# Calculate size * 4
			add $t5, $t5, $v0		# Iterate from A[i][] to A[i+1][]
			addi $t7, $t7, 4		# Increment C to start at C[][a]
			
			addi $t2, $t2, 1		# Increment counter by 1
			blt $t2, $a3, MADD2_CLoop	# if c < size, repeat
	
		addi $t1, $t1, 4		# Increment counter by bsize
		blt $t1, $a3, MADD2_BLoop	# If b < size, repeat

	addi $t0, $t0, 4		# Increment counter by bsize
	blt $t0, $a3, MADD2_ALoop	# If a < size, repeat

MADD2_End:
jr   $ra		# Return to caller

MADD2_AAMin:
addi $v0, $t0, 4		# aa limiter = a + bsize
blt $v0, $a3, MADD2_AAMin2	# If a + bisze < size, goto AAMin2
add $v0, $zero, $a3		# Else, aa limiter == size (n)

MADD2_AAMin2:
j MADD2_AAMinReturn		# Return to call

MADD2_BBMin:
addi $v0, $t1, 4		# bb limiter = b + bsize
blt $v0, $a3, MADD2_BBMin2	# If b + bsize < size, goto BBMin2
add $v0, $zero, $a3		# Else, bb limiter == size (n)

MADD2_BBMin2:
j MADD2_BBMinReturn		# Return to call
