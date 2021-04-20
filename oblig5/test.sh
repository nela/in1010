#!/bin/bash


if [[ -z $1 ]]; then
  loops=10
else
  loops=$1
fi

for i in {0..$loops};
  do
   adders=$(( $RANDOM % 7 + 2 ))
   mergers=$(( $RANDOM % 7 + 2 ))
   ss=$(( $RANDOM % 7 + 3 ))

   java Main -a $adders -m $mergers -s $ss -d Data/
  done

