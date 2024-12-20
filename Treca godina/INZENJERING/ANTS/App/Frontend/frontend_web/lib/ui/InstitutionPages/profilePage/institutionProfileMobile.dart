import 'dart:convert';
import 'package:flutter/material.dart';
import 'package:frontend_web/models/fullPost.dart';
import 'package:frontend_web/models/institution.dart';
import 'package:frontend_web/models/user.dart';
import 'package:frontend_web/services/api.services.dart';
import 'package:frontend_web/services/token.session.dart';
import 'package:frontend_web/widgets/centeredView/centeredViewRowPost.dart';
import 'package:frontend_web/widgets/post/insRowPost/insRowPostMobile.dart';
import 'package:frontend_web/widgets/post/rowPostMobileWidget.dart';
import 'package:frontend_web/widgets/userProfile/userProfileWidget.dart';

Color greenPastel = Color(0xFF00BFA6);

class InstitutionProfileMobile extends StatefulWidget {
  
  final Institution inst;
  InstitutionProfileMobile(this.inst);

  @override
  _InstitutionProfileMobileState createState() => _InstitutionProfileMobileState(inst);
}

class _InstitutionProfileMobileState extends State<InstitutionProfileMobile> {
  
  Institution institution;
  ScrollController _scrollController;
  List<FullPost> posts;
  
  _InstitutionProfileMobileState(Institution inst1){
    this.institution = inst1;
  }

  _getPosts() async {
    APIServices.getPostsSolvedByInstitution(TokenSession.getToken, institution.id).then((res) {
      Iterable list = json.decode(res.body);
      List<FullPost> listP = List<FullPost>();
      listP = list.map((model) => FullPost.fromObject(model)).toList();
      if (mounted) {
        setState(() {
          posts = listP;
        });
      }
    });
  }

  @override
  void initState() {
    super.initState();
    _getPosts();
  }

 Widget infoInstitution(BuildContext context)
 {
   return Container(
      decoration: BoxDecoration(
        color: Colors.white,
        border: Border(
          bottom: BorderSide(
            color: Colors.black26,
            width: 1.0,
          ),
        ),
      ),
      child: Container(
        margin: EdgeInsets.only(left: 16.0, top: 16.0, right: 16.0),
        child: Column(
          crossAxisAlignment: CrossAxisAlignment.start,
          children: <Widget>[
            SizedBox(height: 10,),
            Row(
              mainAxisAlignment: MainAxisAlignment.spaceAround,
              children: <Widget>[
                userImageWithPlus(),
                SizedBox(width: 10,),
                Expanded(
                  flex: 1,
                  child: Column(
                    mainAxisAlignment: MainAxisAlignment.spaceEvenly,
                    children: <Widget>[
                      Text(
                        institution.name,
                        style: TextStyle(
                          color: Colors.black,
                          fontWeight: FontWeight.bold,
                          fontSize: 20.0,
                        ),
                        textAlign: TextAlign.center,
                      ),
                      SizedBox(height: 4.0),
                      Text(
                        'Lokacija: ' + institution.cityName,
                        style: TextStyle(
                            color: Colors.black),
                      ),
                      SizedBox(height: 4.0),
                      Text(
                        institution.description,
                        style: TextStyle(
                            color: Colors.black),
                        textAlign: TextAlign.justify,
                      ),
                    ],
                  ),
                ),
              ],
            ),
            // For padding
            SizedBox(height: 16.0),
          ],
        ),
      ),
    );
 }

   Widget userImageWithPlus() => Column(
        children: <Widget>[
          Container(
            height: 100.0,
            width: 100.0,
            decoration: BoxDecoration(
              shape: BoxShape.circle,
              image: DecorationImage(
                fit: BoxFit.cover,
                image: NetworkImage(userPhotoURL + institution.photoPath),
              ),
            ),
          ),
          Column(
                  children: <Widget>[
                    Text("Broj rešenih",
                        style: TextStyle(
                            color: Colors.black,
                            fontSize: 16,
                            fontWeight: FontWeight.bold)),
                    Text("${institution.postsNum}",
                        style: TextStyle(
                            color: Colors.black,
                            fontSize: 16,
                            fontWeight: FontWeight.bold))
                  ],
                )

        ],
      );

  @override
  Widget build(BuildContext context) {

    return CenteredViewRowPost(
      child: NestedScrollView(
            controller: _scrollController,
            headerSliverBuilder: (BuildContext context, bool boxIsScrolled) {
              return <Widget>[
                // User information section
                SliverToBoxAdapter(child: infoInstitution(context)),
              ];
            },
            body: ListView.builder(
                    padding: EdgeInsets.only(bottom: 30.0),
                    itemCount: posts == null ? 0 : posts.length,
                    itemBuilder: (BuildContext context, int index) {
                      return InsRowPostMobileWidget(posts[index],1);
                    })
      )
    );
  }
}