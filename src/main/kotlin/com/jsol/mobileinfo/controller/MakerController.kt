package com.jsol.mobileinfo.controller

import com.jsol.mobileinfo.config.utils.BasicControllerMethod
import com.jsol.mobileinfo.domain.maker.dto.request.MakerCreateRequest
import com.jsol.mobileinfo.domain.maker.dto.request.MakerUpdateRequest
import com.jsol.mobileinfo.domain.maker.dto.response.MakerResponse
import com.jsol.mobileinfo.domain.maker.service.MakerService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/v1")
class MakerController(
    private val makerService: MakerService
) {

    @PostMapping("/maker")
    fun createMaker(@RequestBody makerDto: MakerCreateRequest): ResponseEntity<MakerResponse> {
        val maker = makerService.createMaker(makerDto)
        val response = MakerResponse.of(maker)
        return ResponseEntity(response, HttpStatus.CREATED)
    }

    @GetMapping("/maker/{makerId}")
    fun getMakerById(@PathVariable(name = "makerId", required = true) makerId: Long): ResponseEntity<MakerResponse> {
        val maker = makerService.getMakerById(makerId)
        return ResponseEntity<MakerResponse>(maker, HttpStatus.OK)
    }

    @GetMapping("/maker")
    fun getMakers(): ResponseEntity<List<MakerResponse>> {
        val makers = makerService.getMakers()
        return ResponseEntity<List<MakerResponse>>(makers, HttpStatus.OK)
    }

    @PutMapping("/maker")
    fun updateMaker(@RequestBody request: MakerUpdateRequest) {
        makerService.updateMaker(request)
    }

    @DeleteMapping("/maker/{makerId}")
    fun deleteMaker(@PathVariable makerId: Long): String {
        return BasicControllerMethod.delete(makerService.deleteMaker(makerId).equals(Unit::class))
    }
}
