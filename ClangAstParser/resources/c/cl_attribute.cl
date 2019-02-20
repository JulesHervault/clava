kernel void myk(global float* A) 
{
    size_t i = get_global_id(0);
    
     __attribute__((opencl_unroll_hint(2)))
	 
    for (size_t i = 0; i < 12; i++) {
        A[i * 12 + i] = sin((float)i);
    }
}