﻿// <auto-generated />
using System;
using API.Data;
using Microsoft.EntityFrameworkCore;
using Microsoft.EntityFrameworkCore.Infrastructure;
using Microsoft.EntityFrameworkCore.Migrations;
using Microsoft.EntityFrameworkCore.Storage.ValueConversion;

namespace API.Migrations
{
    [DbContext(typeof(bazaContext))]
    [Migration("20200309151732_first")]
    partial class first
    {
        protected override void BuildTargetModel(ModelBuilder modelBuilder)
        {
#pragma warning disable 612, 618
            modelBuilder
                .HasAnnotation("ProductVersion", "3.1.2");

            modelBuilder.Entity("API.Models.User", b =>
                {
                    b.Property<int>("idUser")
                        .ValueGeneratedOnAdd()
                        .HasColumnType("INTEGER");

                    b.Property<DateTime>("datumRegistracije")
                        .HasColumnType("TEXT");

                    b.Property<string>("ime")
                        .HasColumnType("TEXT");

                    b.Property<string>("korisnickoIme")
                        .HasColumnType("TEXT");

                    b.Property<string>("mail")
                        .HasColumnType("TEXT");

                    b.Property<string>("prezime")
                        .HasColumnType("TEXT");

                    b.Property<string>("sifra")
                        .HasColumnType("TEXT");

                    b.HasKey("idUser");

                    b.ToTable("Users");
                });
#pragma warning restore 612, 618
        }
    }
}