package uz.fayz.appjparelationships.controller;

import org.springframework.web.bind.annotation.*;
import uz.fayz.appjparelationships.entity.Address;
import uz.fayz.appjparelationships.entity.University;
import uz.fayz.appjparelationships.payload.UniversityDto;
import uz.fayz.appjparelationships.repository.AddressRepository;
import uz.fayz.appjparelationships.repository.UniversityRepository;

import java.util.List;
import java.util.Optional;

@RestController
public class UniversityController {

    final
    UniversityRepository universityRepository;
    final
    AddressRepository addressRepository;

    public UniversityController(UniversityRepository universityRepository, AddressRepository addressRepository) {
        this.universityRepository = universityRepository;
        this.addressRepository = addressRepository;
    }

    //Read
    @RequestMapping(value = "/university", method = RequestMethod.GET)
    public List<University> getUniversity(){
        return universityRepository.findAll();
    }

    //Create
    @RequestMapping(value = "/university", method = RequestMethod.POST)
    public String addUniversity(@RequestBody UniversityDto universityDto){

        Address address = new Address();

        address.setCity(universityDto.getCity());
        address.setDistrict(universityDto.getDistrict());
        address.setStreet(universityDto.getStreet());
        Address savedAddress = addressRepository.save(address);

        University university = new University();

        university.setName(universityDto.getName());
        university.setAddress(savedAddress);
        universityRepository.save(university);

        return "University added successfully!";
    }

    @RequestMapping(value = "/university/{id}", method = RequestMethod.PUT)
    public String updateUniversity(@PathVariable Integer id, @RequestBody UniversityDto universityDto){
        Optional<University> optionalUniversity = universityRepository.findById(id);
        if (optionalUniversity.isPresent()){

            Address address = new Address();
            address.setStreet(universityDto.getStreet());
            address.setCity(universityDto.getCity());
            address.setDistrict(universityDto.getDistrict());

            Address address1 = addressRepository.save(address);

            University university = optionalUniversity.get();
            university.setName(universityDto.getName());
            university.setAddress(address1);

            universityRepository.save(university);

            return "University successfully updated!";

        }else
            return "University not found!";
    }

    @RequestMapping(value = "/university/{id}", method = RequestMethod.DELETE)
    public String deleteUniversity(@PathVariable Integer id){
        universityRepository.deleteById(id);
        return "University successfully deleted!";
    }
}
