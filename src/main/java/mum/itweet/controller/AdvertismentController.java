package mum.itweet.controller;

import mum.itweet.model.Advertisement;
import mum.itweet.model.dto.AdAgeFilterDto;
import mum.itweet.model.dto.AdGenderFilterDto;
import mum.itweet.model.dto.UserAdvertisementDto;
import mum.itweet.service.AdvertisementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/ads")
public class AdvertismentController {

    @Autowired
    AdvertisementService advertisementService;

    @GetMapping("/")
    public List<Advertisement>getAll(){
        return advertisementService.getAllAdvertisements();
    }

    @GetMapping("/{id}")
    public Advertisement getById(@PathVariable long id){
        return advertisementService.getAdvertisementById(id);
    }
    // No Dto because you create a whole advertisement
    @PostMapping("/")
    public Advertisement create(@RequestBody Advertisement advertisement){
        return advertisementService.create(advertisement);
    }
    @PostMapping("/update/{id}")
    public Advertisement update(@PathVariable long id,@RequestBody Advertisement advertisement){
        return advertisementService.update(id,advertisement);
    }

    @PostMapping("/delete/{id}")
    public void delete(@PathVariable long id ){
        advertisementService.deleteAdvertisement(id);
    }

    @GetMapping("/activerandom")
    public Advertisement getRandomActiveAd(){
        return advertisementService.getRandomActiveAdvertisement();
    }
    @PostMapping("/ageFilter")
    public List<Advertisement> getAdsByAgeFilter(@RequestBody AdAgeFilterDto ageFilterDto){
        return advertisementService.getAdsByAge(ageFilterDto.getAgeFrom(),ageFilterDto.getAgeTo());
    }
    @PostMapping("/genderFilter")
    public List<Advertisement> getAdsByGenderFilter(@RequestBody AdGenderFilterDto adGenderFilterDto){
            return advertisementService.getAdsByGender(adGenderFilterDto.getGender());
    }
    @GetMapping("/userAd")
    public Advertisement getUserAd(@RequestBody UserAdvertisementDto userAdvertisementDto){
        return advertisementService.getRandomAvilableAd(userAdvertisementDto.getUserId());
    }

}
