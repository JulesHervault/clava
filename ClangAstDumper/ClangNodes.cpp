//
// Created by JoaoBispo on 21/03/2018.
//

#include "ClangNodes.h"
#include "ClangEnums.h"

#include "clang/Lex/Lexer.h"

#include <bitset>


using namespace clang;

const std::string clava::getClassName(const Decl* D) {
    const std::string kindName = D->getDeclKindName();
    return kindName + "Decl";
}

const std::string clava::getClassName(const Stmt* S) {
    const std::string className = S->getStmtClassName();
    return className;
}

const std::string clava::getClassName(const Type* T) {
    const std::string kindName = T->getTypeClassName();
    return kindName + "Type";
}

const std::string clava::getClassName(const Attr* A) {
    const std::string kindName =  clava::ATTRIBUTES[A->getKind()];
    return kindName + "Attr";
}


void clava::dumpSourceRange(ASTContext *Context, SourceLocation startLoc, SourceLocation endLoc) {
    //llvm::errs() << "<SourceRange Dump>\n";

    //llvm::errs() << id << "\n";

    // All components of the source range will be dumped
    const SourceManager& SM = Context->getSourceManager();

    SourceLocation startSpellingLoc = SM.getSpellingLoc(startLoc);
    PresumedLoc startPLoc = SM.getPresumedLoc(startSpellingLoc);

    if (startPLoc.isInvalid()) {
        llvm::errs() << "<invalid>\n";
        return;
    }


    // Dump start location
    llvm::errs() << startPLoc.getFilename() << "\n";
    llvm::errs() << startPLoc.getLine() << "\n";
    llvm::errs() << startPLoc.getColumn() << "\n";

    if(startLoc == endLoc) {
        llvm::errs() << "<end>\n";
        return;
    }

    SourceLocation endSpellingLoc = SM.getSpellingLoc(endLoc);
    PresumedLoc endPLoc = SM.getPresumedLoc(endSpellingLoc);

    if(endPLoc.isInvalid()) {
        llvm::errs() << "<end>\n";
        return;
    }

    const char* endFilename = endPLoc.getFilename();
    if(!endFilename) {
        endFilename = startPLoc.getFilename();
    }

    unsigned int endLine = endPLoc.getLine();
    if(!endLine) {
        endLine = startPLoc.getLine();
    }

    unsigned int endCol = endPLoc.getColumn();
    if(!endCol) {
        endCol = startPLoc.getColumn();
    }

    // Dump end location
    llvm::errs() << endFilename << "\n";
    llvm::errs() << endLine << "\n";
    llvm::errs() << endCol << "\n";
}

void clava::dumpSourceInfo(ASTContext *Context, SourceLocation begin, SourceLocation end) {

    // Original source range
    clava::dumpSourceRange(Context, begin, end);

    // If it is a macro
    bool isMacro = begin.isMacroID() || end.isMacroID();
    clava::dump(isMacro);

    // Spelling location, if macro
    if(isMacro) {
        clava::dumpSourceRange(Context, Context->getSourceManager().getSpellingLoc(begin), Context->getSourceManager().getSpellingLoc(end));
    }

}


const std::string clava::getId(const void* addr, int id) {
    //if(addr == nullptr || addr == 0) {
    //    return "nullptr";
    //}

    std::stringstream ss;
    ss <<  addr << "_" << id;

    return ss.str();
}

const std::string clava::getId(const Decl* addr, int id) {
    if(addr == nullptr) {
        return "nullptr_decl";
    }

    return getId((void*) addr, id);
}

const std::string clava::getId(const Stmt* addr, int id) {
    if(addr == nullptr) {
        return "nullptr_stmt";
    }

    return getId((void*) addr, id);
}

const std::string clava::getId(const Expr* addr, int id) {
    if(addr == nullptr) {
        return "nullptr_expr";
    }

    return getId((void*) addr, id);
}

const std::string clava::getId(const Type* addr, int id) {
    if(addr == nullptr) {
        return "nullptr_type";
    }

    return getId((void*) addr, id);
}

const std::string clava::getId(const QualType &addr, int id) {
    if(addr.isNull()) {
        return "nullptr_type";
    }

    return getId(addr.getAsOpaquePtr(), id);
}

const std::string clava::getId(const Attr* addr, int id) {
    if(addr == nullptr) {
        return "nullptr_attr";
    }

    return getId((void*) addr, id);
}


void clava::dump(bool boolean) {
    llvm::errs() << boolean << "\n";
}

void clava::dump(int integer) {
    llvm::errs() << integer << "\n";
}

void clava::dump(double aDouble) {
    llvm::errs() << aDouble << "\n";
}

void clava::dump(unsigned int integer) {
    llvm::errs() << integer << "\n";
}

void clava::dump(size_t integer) {
    llvm::errs() << integer << "\n";
}

void clava::dump(const std::string& string) {
    llvm::errs() << string << "\n";
}

void clava::dump(const char string[]) {
    dump(std::string(string));
}

void clava::dump(const std::vector<std::string> &strings) {
    // Number of attributes
    dump((unsigned int)strings.size());

    // Dump each attribute address
    for(auto string : strings) {
        dump(string);
    }
}

void clava::dump(const std::vector<Attr*> &attributes, const int id) {
    // Number of attributes
    dump((unsigned int)attributes.size());

    // Dump each attribute address
    for(auto attr : attributes) {
        dump(clava::getId(attr, id));
        dump(attr->isImplicit());
        dump(attr->isInherited());
        dump(attr->isLateParsed());
        dump(attr->isPackExpansion());
    }
/*
    // Dump each attribute
    for(auto attr : attributes) {
        dump(clava::ATTRIBUTES[attr->getKind()]);
        dump(attr->isImplicit());
        dump(attr->isInherited());
        dump(attr->isLateParsed());
        dump(attr->isPackExpansion());
    }
*/
}


void clava::dump(const QualType& type, int id) {
/*
    if(type.isNull()) {
        dump("nullptr");
    }
*/
    //dump(getId(type.getTypePtr(), id));
    // QUALTYPE EXP
    //dump(getId(type.getAsOpaquePtr(), id));
    dump(getId(type, id));

    // Check if QualType is the same as the underlying type
    /*
    if((void*) type.getTypePtr() == type.getAsOpaquePtr()) {

        llvm::errs() << "QualType " << getId(type.getAsOpaquePtr(), id) << " opaque ptr and type ptr are the same\n";
    } else {
        llvm::errs() << "QualType " << getId(type.getAsOpaquePtr(), id) << " type ptr is different -> " << getId(type.getTypePtr(), id) << "\n";
    }
     */

    // Dump QualType
    /*
    if(dumpType(T)) {
        return;
    }
    */




}

void clava::dump(const Qualifiers& qualifiers, ASTContext* Context) {
    auto c99Qualifiers = qualifiers.getCVRQualifiers();
    const int numBits = std::numeric_limits<decltype(c99Qualifiers)>::digits;
    size_t numSetBits = std::bitset<numBits>(c99Qualifiers).count();

    // Dumps the number of C99 qualifiers, and then the name of the qualifiers
    clava::dump((int) numSetBits);
    if(qualifiers.hasConst()) {clava::dump("CONST");}
    if(qualifiers.hasRestrict()) {
        if(Context->getPrintingPolicy().LangOpts.C99)
            clava::dump("RESTRICT_C99");
        else
            clava::dump("RESTRICT");
    }
    if(qualifiers.hasVolatile()) {clava::dump("VOLATILE");}

}



/**
 * Taken from: https://stackoverflow.com/questions/11083066/getting-the-source-behind-clangs-ast
 * @param Context
 * @param start
 * @param end
 * @return
 */
const std::string clava::getSource(ASTContext *Context, SourceRange sourceRange) {
    //const SourceManager &sm = Context->getSourceManager();

  /*

    clang::SourceLocation b(start), _e(end);

    clang::SourceLocation e(clang::Lexer::getLocForEndOfToken(_e, 0, sm, Context->getLangOpts()));

    return std::string(sm.getCharacterData(b),
                                sm.getCharacterData(e)-sm.getCharacterData(b));
*/
    // (T, U) => "T,,"

    const SourceManager &sm = Context->getSourceManager();


    SourceLocation begin = sourceRange.getBegin();
    SourceLocation end = sourceRange.getEnd();
    if (begin.isMacroID()) {
        //llvm::errs() << "Begin is macro:" << begin.printToString(sm) << "\n";
        begin = sm.getSpellingLoc(begin);
        //llvm::errs() << "Begin spelling loc:" << begin.printToString(sm) << "\n";
    } else {
        //llvm::errs() << "Begin is not macro:" << begin.printToString(sm) << "\n";
    }

    if (end.isMacroID()) {
        //llvm::errs() << "End is macro:" << end.printToString(sm) << "\n";
        end = sm.getSpellingLoc(end);
        //llvm::errs() << "End spelling loc:" << end.printToString(sm) << "\n";
    } else {
        //llvm::errs() << "End is not macro:" << begin.printToString(sm) << "\n";
    }



    std::string text = Lexer::getSourceText(CharSourceRange::getTokenRange(begin, end), sm, LangOptions(), 0);
    if (text.size() > 0 && (text.at(text.size()-1) == ',')) { //the text can be ""
        std::string otherText = Lexer::getSourceText(CharSourceRange::getCharRange(begin, end), sm, LangOptions(), 0);
        return  otherText + "\n%CLAVA_SOURCE_END%";
    }

    return text + "\n%CLAVA_SOURCE_END%";


}

void clava::dump(NestedNameSpecifier* qualifier, ASTContext* Context) {

    // Dump qualifier
    if (qualifier != nullptr) {
        std::string qualifierStr;
        llvm::raw_string_ostream qualifierStream(qualifierStr);
        qualifier->print(qualifierStream, Context->getPrintingPolicy());
        clava::dump(qualifierStream.str());
    } else {
        clava::dump("");
    }
}


/*
llvm::raw_ostream clava::stringStream() {
    std::string stringStream;
    llvm::raw_string_ostream llvmStringStream(stringStream);
    return llvmStringStream;
}
 */
/*
void clava::dump(llvm::raw_string_ostream llvmStringStream) {
    dump(llvmStringStream.str());
}
*/