package com.example.rxjava_okhttp_retrofit_text;

public class BikeLoader {
    private BikeService bikeService;

    public BikeLoader() {
        bikeService = RetrofitServiceManager.getInstance().create(BikeService.class);
    }

//    public Observable<List<Bike>> getBike(int limit, int offset) {
//        return observe(bikeService.getBike(limit, offset))
//                .map(new Func1<Bike, List<Bike>>) {
//            @Override
//                    public List<Bike> call()
//        }
//    }
}
