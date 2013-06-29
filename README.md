log320-lab3-line-of-action
==========================

Profiling
---------

[hporf](http://docs.oracle.com/javase/7/docs/technotes/samples/hprof.html) can be use to profile application CPU and heap usage:

    java -agentlib:hprof=cpu=times Main
