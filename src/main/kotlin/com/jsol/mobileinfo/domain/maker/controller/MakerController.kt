package com.jsol.mobileinfo.domain.maker.controller

import com.jsol.mobileinfo.domain.maker.dto.request.MakerCreateRequest
import com.jsol.mobileinfo.domain.maker.dto.request.MakerUpdateRequest
import com.jsol.mobileinfo.domain.maker.dto.response.MakerResponse
import com.jsol.mobileinfo.domain.maker.service.MakerService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController("/api/v1")
class MakerController (
    private val makerService: MakerService
) {

    @PostMapping("/maker")
    fun createMaker(@RequestBody makerDto: MakerCreateRequest): ResponseEntity<Unit>{
        makerService.createMaker(makerDto)
        return ResponseEntity<Unit>(HttpStatus.CREATED)
    }

    @GetMapping("/maker/{makerId}")
    fun getMakerById(@PathVariable(name="makerId", required = true) makerId: Long): ResponseEntity<MakerResponse>{
        val maker = makerService.getMakerById(makerId)
        return ResponseEntity<MakerResponse>(maker, HttpStatus.OK)
    }

    @GetMapping("/maker")
    fun getMakers(): ResponseEntity<List<MakerResponse>>{
        val makers = makerService.getMakers()
        return ResponseEntity<List<MakerResponse>>(makers, HttpStatus.OK)
    }

    @PutMapping("/maker")
    fun updateMaker(@RequestBody request: MakerUpdateRequest){
        makerService.updateMaker(request)
    }

    @DeleteMapping("/maker/{makerId}")
    fun deleteMaker(@PathVariable makerId: Long){
        makerService.deleteMaker(makerId)
    }
}