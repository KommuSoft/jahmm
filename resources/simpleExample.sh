#!/bin/bash


#
# CLI-related variables
#

cli="java be.ac.ulg.montefiore.run.jahmm.apps.cli.Cli"
opdf="-opdf integer -r 2"


#
# File names
#

tmp_dir="."
hmm="${tmp_dir}/hmmExample.hmm"
initHmm="${tmp_dir}/hmmExampleInit.hmm"
learntHmm="${tmp_dir}/hmmExampleLearnt.hmm"
seqs="${tmp_dir}/hmmExample.seq"


#
# Functions
#

create_files ()
{
    cat > ${hmm} <<EOF
Hmm v1.0

NbStates 2

State
Pi 0.95
A 0.95 0.05
IntegerOPDF [0.95 0.05 ]

State
Pi 0.05
A 0.10 0.90
IntegerOPDF [0.20 0.80 ]
EOF

    cat > ${initHmm} <<EOF
Hmm v1.0

NbStates 2

State
Pi 0.50
A 0.80 0.20
IntegerOPDF [0.80 0.20 ]

State
Pi 0.05
A 0.20 0.80
IntegerOPDF [0.10 0.90 ]
EOF
}

erase_files ()
{
    rm -f ${hmm} ${initHmm} ${learntHmm} ${seqs}
}


#
# Main section
#

# Create sample HMMs
create_files;

# Generate sequences of observations using ${hmm}
${cli} generate ${opdf} -i ${hmm} -os ${seqs}

# Baum-Welch learning based on ${initHmm}
cp ${initHmm} ${learntHmm}
for i in 0 1 2 3 4 5 6 7
do
 echo $i `${cli} distance-kl ${opdf} -i ${learntHmm} -ikl ${hmm}`
 ${cli} learn-bw ${opdf} -i ${initHmm} -o ${learntHmm} -is ${seqs} -ni 1
done

# Print resulting HMM
echo
echo "Resulting HMM:"
${cli} print -i ${learntHmm}

# Erase the files created
erase_files;

exit 0
