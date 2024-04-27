package cs489.project.service.impl;

import cs489.project.dto.restaurants.RestaurantsDto;
import cs489.project.dto.specification.SpecificationDto;
import cs489.project.model.RestaurantManager;
import cs489.project.model.Specification;
import cs489.project.repository.ResManagerRepository;
import cs489.project.service.ResManagerService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ResManagerServiceImpl implements ResManagerService {
    private ResManagerRepository resManagerRepository;
    public ResManagerServiceImpl(ResManagerRepository resManagerRepository) {
        this.resManagerRepository = resManagerRepository;
    }

    @Override
    public List<RestaurantsDto> getRestaurants() {
        return resManagerRepository.findAll()
                .stream()
                .map(a -> new RestaurantsDto(
                        a.getResId(),
                        a.getResName(),
                        a.getEmail(),
                        a.getPhoneNumber(),
                        a.getAddress(),
                        a.getCapacity(),
                        a.getKeywords().stream()
                                .map(b -> new SpecificationDto(
                                        b.getSpecId(),
                                        b.getKeyword()
                                )).toList()
                )).toList();
    }

    @Override
    public List<RestaurantsDto> searchRestaurants(String keyword) {
        List<RestaurantManager> list = resManagerRepository.findByResNameContainingOrAddressContainingOrEmailContainingOrPhoneNumberContaining(
            keyword, keyword, keyword, keyword
        );
        return list.stream()
                .map(a -> new RestaurantsDto(
                        a.getResId(),
                        a.getResName(),
                        a.getEmail(),
                        a.getPhoneNumber(),
                        a.getAddress(),
                        a.getCapacity(),
                        a.getKeywords().stream()
                                .map(b -> new SpecificationDto(
                                        b.getSpecId(),
                                        b.getKeyword()
                                )).toList()
                )).toList();
    }
}
