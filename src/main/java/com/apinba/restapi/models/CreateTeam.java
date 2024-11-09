package com.apinba.restapi.models;

public record CreateTeam(
    String name,
    String fullName,
    String abbreviation,
    String city,
    String conference,
    String division) {}
