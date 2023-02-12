package com.egeuniversity.Tez.Service.Address;

import com.egeuniversity.Tez.Model.Address.Address;
import com.egeuniversity.Tez.Model.Address.AddressRequestDTO;

public interface AddressService {
    Address addAddress(AddressRequestDTO addressRequestDTO);
    Address getAddress(Integer id);
    void deleteAddress(Integer id);
    Address assembleAddress(AddressRequestDTO addressRequestDTO);
    Address updateAddress(Integer id, AddressRequestDTO addressDTO);
}
