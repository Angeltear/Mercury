package com.technical.mercury.services;

import com.technical.mercury.model.Department;
import com.technical.mercury.model.Location;
import com.technical.mercury.repository.DepartmentRepository;
import com.technical.mercury.repository.LocationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LocationService{

    private final LocationRepository locationRepository;

    public List<Location> getAll(){
        return locationRepository.findAll();
    }

    public Location save(Location location){return locationRepository.save(location);}
}
