package com.example.ecommerce.security.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.ecommerce.entity.Pengguna;
import com.example.ecommerce.repository.PenggunaRepository;

//Service untuk mengimplementasikan UserDetailsService
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    PenggunaRepository penggunaRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Pengguna pengguna = penggunaRepository.findById(username)
                .orElseThrow(() -> new UsernameNotFoundException(
                        "Pengguna dengan username " + username + " tidak ditemukan"));
        return UserDetailsImpl.build(pengguna);
    }

}
