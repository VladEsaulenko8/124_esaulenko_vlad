def sum_array(arr):
    if arr is None or len(arr) < 3:
        return 0
    else:
        arr.remove(max(arr))
        arr.remove(min(arr))
        return sum(arr)
    