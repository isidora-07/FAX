import 'dart:convert';
import 'package:flutter/material.dart';
import 'package:frontend_web/models/comment.dart';
import 'package:frontend_web/services/api.services.dart';
import 'package:frontend_web/services/token.session.dart';
import 'package:frontend_web/ui/adminPages/manageInstitution/manageInstitutionDesktop.dart';
import 'package:frontend_web/ui/homePage.dart';
import 'package:frontend_web/ui/postPage.dart';
import 'circleImageWidget.dart';


class CommentsWidget extends StatefulWidget {
  final int postId;
  CommentsWidget(this.postId);

   @override
  _CommentsWidgetState createState() => _CommentsWidgetState(postId);

}

class _CommentsWidgetState extends State<CommentsWidget> {
  int postId;
  _CommentsWidgetState(int id)
  {
    this.postId = id;
  }

  List<Comment> listComments;
  //get comments for specific post id
  _getComments(int postId) {
    APIServices.getComments(TokenSession.getToken, postId).then((res) {
      Iterable list = json.decode(res.body);
      List<Comment> listComms = List<Comment>();
      listComms = list.map((model) => Comment.fromObject(model)).toList();
      
      if(mounted)
      {
        setState(()
        {
          listComments = listComms;
        });
      }
    });
  }

  @override
  void initState()
  {
    _getComments(this.postId);
    super.initState();
  }


  @override
  Widget build(BuildContext context) {
    return buildCommentList();
  }

  showAlertDialog(BuildContext context, int id) {
    //set up buttons
    Widget okButton = FlatButton(
      child: Text("Obriši", style: TextStyle(color: greenPastel),),
      onPressed: () {
        APIServices.deleteComment(TokenSession.getToken, id);
        Navigator.pushReplacement(
          context,
          MaterialPageRoute(builder: (context) => PostPage(globalUser)),
        );
        },
    );
     Widget notButton = FlatButton(
      child: Text("Otkaži", style: TextStyle(color: greenPastel),),
      onPressed: () {
        Navigator.pop(context);
        },
    );

    //set up the AlertDialog
    AlertDialog alert = AlertDialog(
      title: Text("Brisanje komentara"),
      content: Text("Da li ste sigurni da želite da obrišete komentar?"),
      actions: [
        okButton,
        notButton,
      ],
    );

    //show the dialog
    showDialog(
      context: context,
      builder: (BuildContext context) {
        return alert;
      },
    );
  }

  //building comment list
  Widget buildCommentList() {
    return ListView.builder(
      scrollDirection: Axis.vertical,
      shrinkWrap: true,
      itemCount: listComments == null ? 0 : listComments.length,
      itemBuilder: (BuildContext context, int index) {
        return Container(
          child: Center(
            child: Column(
              crossAxisAlignment: CrossAxisAlignment.stretch,
              children: <Widget>[
                Container(
                  color: Colors.white,
                  padding: EdgeInsets.all(10),
                  margin: EdgeInsets.only(top: 5),
                  child: Row(children: [
                    CircleImage(
                      "http://127.0.0.1:60676//" + listComments[index].photoPath,
                      imageSize: 56.0,
                      whiteMargin: 2.0,
                      imageMargin: 6.0,
                    ),
                    Container(
                      width: 260,
                      padding: EdgeInsets.all(10),
                      child: Column(
                        crossAxisAlignment: CrossAxisAlignment.start,
                        children: [
                          Row(children: [
                            Text(listComments[index].username,
                                style: TextStyle(fontWeight: FontWeight.bold)),
                            Expanded(child: SizedBox()),
                          ]),
                          Text(listComments[index].description,
                              style: TextStyle( fontStyle: FontStyle.italic, fontSize: 15))
                        ],
                      ),
                    ),
                    Expanded(child: SizedBox()),
                    IconButton(
                      icon: Icon(Icons.delete_forever),
                      onPressed: () {
                        showAlertDialog(context, listComments[index].id);
                      },
                    ),
                  ])
                ),
            ],
          ),
        ));
      },
    );
  }
}